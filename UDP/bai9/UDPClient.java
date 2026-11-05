package UDP.bai9;

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
        String qCode = "LqNsFeK4";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive "requestId;str1:pos1,str2:pos2,..."
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            System.out.println("Received: " + received);

            int sep = received.indexOf(';');
            String requestId = received.substring(0, sep);
            String data = received.substring(sep + 1).trim();

            // c. Sort substrings by their position number
            List<String[]> items = new ArrayList<>();
            for (String item : data.split(",")) {
                item = item.trim();
                if (item.isEmpty()) continue;
                int colon = item.lastIndexOf(':');
                items.add(new String[]{item.substring(0, colon), item.substring(colon + 1).trim()});
            }
            items.sort((x, y) -> Integer.compare(Integer.parseInt(x[1]), Integer.parseInt(y[1])));

            List<String> sorted = new ArrayList<>();
            for (String[] it : items) sorted.add(it[0]);

            String result = requestId + ";" + String.join(",", sorted);
            byte[] resultData = result.getBytes();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + result);
        }
        // d. Socket closed by try-with-resources
    }
}
