package UDP.bai5;

import UDP.Student;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "36.50.135.242";
        int port = 2209;
        String studentCode = "B23DCDT170";
        String qCode = "wxuDvlfe";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive: 8 bytes requestId + serialized Student
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String requestId = new String(packet.getData(), 0, 8);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8));
            Student student = (Student) ois.readObject();
            System.out.println("Received: " + requestId + " " + student);

            // c. Normalize name and build email
            String[] words = student.getName().trim().toLowerCase().split("\\s+");
            StringBuilder name = new StringBuilder();
            for (String w : words) {
                if (name.length() > 0) name.append(" ");
                name.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
            }
            student.setName(name.toString());

            // nguyen van tuan nam -> namnvt@ptit.edu.vn
            StringBuilder email = new StringBuilder(words[words.length - 1]);
            for (int i = 0; i < words.length - 1; i++) {
                email.append(words[i].charAt(0));
            }
            email.append("@ptit.edu.vn");
            student.setEmail(email.toString());

            // Send: 8 bytes requestId + serialized modified Student
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes(), 0, 8);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();

            byte[] resultData = baos.toByteArray();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + requestId + " " + student);
        }
        // d. Socket closed by try-with-resources
    }
}
