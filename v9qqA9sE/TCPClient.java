package v9qqA9sE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPClient {
    public static void main(String[] args) {
        String serverHost = "36.50.135.242";
        int serverPort = 2208;
        String studentCode = "B23DCDT170";
        String qCode = "v9qqA9sE";

        try (Socket socket = new Socket(serverHost, serverPort)) {
            // Set timeout for communication (5 seconds)
            socket.setSoTimeout(5000);

            // Wrap streams with BufferedReader and BufferedWriter
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // a. Send studentCode and qCode formatted as "studentCode;qCode"
            String request = studentCode + ";" + qCode;
            writer.write(request);
            writer.newLine();
            writer.flush();
            System.out.println("Sent step a: " + request);

            // b. Receive list of domains from server
            String response = reader.readLine();
            System.out.println("Received step b: " + response);

            if (response != null && !response.isEmpty()) {
                // c. Filter domains ending with ".edu"
                String[] domains = response.split(",");
                List<String> eduDomains = new ArrayList<>();
                for (String domain : domains) {
                    domain = domain.trim();
                    if (domain.toLowerCase().endsWith(".edu")) {
                        eduDomains.add(domain);
                    }
                }

                // Join back with ", "
                String result = String.join(", ", eduDomains);
                writer.write(result);
                writer.newLine();
                writer.flush();
                System.out.println("Sent step c: " + result);
            }

            // d. Close streams and socket (handled automatically by try-with-resources)
            reader.close();
            writer.close();
            System.out.println("Closed connection successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
