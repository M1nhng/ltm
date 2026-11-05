package UDP.bai6;

import UDP.Customer;

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
        String qCode = "V3wGaY0r";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // Receive: 8 bytes requestId + serialized Customer
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String requestId = new String(packet.getData(), 0, 8);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8));
            Customer customer = (Customer) ois.readObject();
            System.out.println("Received: " + requestId + " " + customer);

            String[] words = customer.getName().trim().toLowerCase().split("\\s+");

            // a. nguyen van hai duong -> DUONG, Nguyen Van Hai
            StringBuilder name = new StringBuilder(words[words.length - 1].toUpperCase()).append(",");
            for (int i = 0; i < words.length - 1; i++) {
                name.append(" ").append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
            }
            customer.setName(name.toString());

            // b. mm-dd-yyyy -> dd/mm/yyyy
            String[] d = customer.getDayOfBirth().trim().split("-");
            customer.setDayOfBirth(d[1] + "/" + d[0] + "/" + d[2]);

            // c. nguyen van hai duong -> nvhduong
            StringBuilder userName = new StringBuilder();
            for (int i = 0; i < words.length - 1; i++) {
                userName.append(words[i].charAt(0));
            }
            userName.append(words[words.length - 1]);
            customer.setUserName(userName.toString());

            // Send: 8 bytes requestId + serialized modified Customer
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes(), 0, 8);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(customer);
            oos.flush();

            byte[] resultData = baos.toByteArray();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + requestId + " " + customer);
        }
        // Socket closed by try-with-resources
    }
}
