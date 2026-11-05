package UDP.bai10;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCDT170";
        String qCode = "etPpdXEG";

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
            String data = received.substring(sep + 1);

            // c. Lowercase, remove punctuation, collapse whitespace, replace spaces with "-"
            String answer = data.toLowerCase()
                    .replaceAll("\\p{Punct}", "")
                    .trim()
                    .replaceAll("\\s+", "-");

            // d. Send "requestId;answer"
            String result = requestId + ";" + answer;
            byte[] resultData = result.getBytes();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + result);
        }
        // e. Socket closed by try-with-resources
    }
}
