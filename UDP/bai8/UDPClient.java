package UDP.bai8;

import UDP.Book;

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
        String qCode = "fTSiCNqc";

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName(host);

            // a. Send ";studentCode;qCode"
            byte[] sendData = (";" + studentCode + ";" + qCode).getBytes();
            socket.send(new DatagramPacket(sendData, sendData.length, address, port));

            // b. Receive: 8 bytes requestId + serialized Book
            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String requestId = new String(packet.getData(), 0, 8);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(packet.getData(), 8, packet.getLength() - 8));
            Book book = (Book) ois.readObject();
            System.out.println("Received: " + requestId + " " + book);

            // c1. Title: capitalize first letter of each word
            book.setTitle(capitalizeWords(book.getTitle()));

            // c2. Author: "HO, Ten" -> family name (first word) uppercase, rest capitalized
            String[] a = book.getAuthor().trim().toLowerCase().split("\\s+");
            StringBuilder author = new StringBuilder(a[0].toUpperCase()).append(",");
            for (int i = 1; i < a.length; i++) {
                author.append(" ").append(Character.toUpperCase(a[i].charAt(0))).append(a[i].substring(1));
            }
            book.setAuthor(author.toString());

            // c3. ISBN-13 -> 978-3-16-148410-0 (3-1-2-6-1)
            String digits = book.getIsbn().replaceAll("[^0-9Xx]", "");
            if (digits.length() == 13) {
                digits = digits.substring(0, 3) + "-" + digits.charAt(3) + "-" + digits.substring(4, 6)
                        + "-" + digits.substring(6, 12) + "-" + digits.charAt(12);
            }
            book.setIsbn(digits);

            // c4. publishDate yyyy-mm-dd -> mm/yyyy
            String[] d = book.getPublishDate().trim().split("-");
            book.setPublishDate(d[1] + "/" + d[0]);

            // d. Send: 8 bytes requestId + serialized modified Book
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes(), 0, 8);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(book);
            oos.flush();

            byte[] resultData = baos.toByteArray();
            socket.send(new DatagramPacket(resultData, resultData.length, address, port));
            System.out.println("Sent: " + requestId + " " + book);
        }
        // Socket closed by try-with-resources
    }

    private static String capitalizeWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (String w : s.trim().toLowerCase().split("\\s+")) {
            if (w.isEmpty()) continue;
            if (sb.length() > 0) sb.append(" ");
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        }
        return sb.toString();
    }
}
