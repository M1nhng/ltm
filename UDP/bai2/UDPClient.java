package UDP.bai2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCDT170";
        String qCode = "p574Zs8t";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive "requestId;data"
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength()).trim();
            System.out.println("Received: " + received);

            int sep = received.indexOf(';');
            String requestId = received.substring(0, sep);
            String data = received.substring(sep + 1).trim();

            // c. Most frequent character (tie -> the one appearing first), 1-based positions
            Map<Character, Integer> count = new LinkedHashMap<>();
            for (char c : data.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
            char best = 0;
            int bestCount = 0;
            for (Map.Entry<Character, Integer> e : count.entrySet()) {
                if (e.getValue() > bestCount) {
                    best = e.getKey();
                    bestCount = e.getValue();
                }
            }
            StringBuilder positions = new StringBuilder();
            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == best) positions.append(i + 1).append(",");
            }

            // Format: requestId;char:pos1,pos2,
            String result = requestId + ";" + best + ":" + positions;
            byte[] resultData = result.getBytes();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + result);
        }
        // d. Socket closed by try-with-resources
    }
}
