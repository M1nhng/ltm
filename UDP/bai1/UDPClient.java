package UDP.bai1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCDT170";
        String qCode = "WrnSuNZ1";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive "requestId;n;A1,A2,...,Am"
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            System.out.println("Received: " + received);

            String[] parts = received.split(";");
            String requestId = parts[0];
            int n = Integer.parseInt(parts[1].trim());

            // c. Find missing values in 1..n
            boolean[] present = new boolean[n + 1];
            if (parts.length > 2) {
                for (String s : parts[2].split(",")) {
                    s = s.trim();
                    if (s.isEmpty()) continue;
                    int v = Integer.parseInt(s);
                    if (v >= 1 && v <= n) present[v] = true;
                }
            }
            List<String> missing = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!present[i]) missing.add(String.valueOf(i));
            }

            String result = requestId + ";" + String.join(",", missing);
            byte[] resultData = result.getBytes();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + result);
        }
        // d. Socket closed by try-with-resources
    }
}
