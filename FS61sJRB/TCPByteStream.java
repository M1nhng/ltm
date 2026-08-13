package FS61sJRB;

import java.net.Socket;

public class TCPByteStream {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2206);

        socket.getOutputStream().write("B23DCDT170;FS61sJRB".getBytes());
        
        byte[] buff = new byte[1024];
        int bytes = socket.getInputStream().read(buff);

        int n = Integer.parseInt(new String(buff, 0, bytes));
        

        socket.close();
    }
}
