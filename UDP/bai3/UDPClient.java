package UDP.bai3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2207;
        String studentCode = "B23DCDT170";
        String qCode = "YfXNnd8s";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive "requestId;a1,a2,...,a50"
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            System.out.println("Received: " + received);

            int sep = received.indexOf(';');
            String requestId = received.substring(0, sep);
            String[] numbers = received.substring(sep + 1).split(",");

            // c. Find max and min
            long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
            for (String s : numbers) {
                s = s.trim();
                if (s.isEmpty()) continue;
                long v = Long.parseLong(s);
                max = Math.max(max, v);
                min = Math.min(min, v);
            }

            String result = requestId + ";" + max + "," + min;
            byte[] resultData = result.getBytes();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + result);
        }
        // d. Socket closed by try-with-resources
    }
}
