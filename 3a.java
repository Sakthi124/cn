//echoserver
import java.net.*;
import java.io.*;

public class Server {
    public static final int PORT = 4000;

    public static void main(String args[]) {
        ServerSocket sersock = null;
        Socket sock = null;

        try {
            sersock = new ServerSocket(PORT);
            System.out.println("Server Started: " + sersock);

            try {
                sock = sersock.accept();
                System.out.println("Client Connected: " + sock);

                DataInputStream ins = new DataInputStream(sock.getInputStream());
                System.out.println("Client says: " + ins.readLine());

                PrintStream ios = new PrintStream(sock.getOutputStream());
                ios.println("Hello from server");

                ios.close();
                ins.close();
                sock.close();
            }
            catch (SocketException se) {
                System.out.println("Server Socket problem: " + se.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't start: " + e.getMessage());
        }

        if (sock != null) {
            System.out.println("Connection from: " + sock.getInetAddress());
        }
    }
}


//echoclient
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String args[]) {
        Socket sock = null;

        System.out.println("Trying to connect to server...");

        try {
            sock = new Socket(InetAddress.getLocalHost(), Server.PORT);

            PrintStream ps = new PrintStream(sock.getOutputStream());
            ps.println("Hi from client");

            DataInputStream is = new DataInputStream(sock.getInputStream());
            System.out.println("Server says: " + is.readLine());

            ps.close();
            is.close();
            sock.close();
        }
        catch (SocketException e) {
            System.out.println("SocketException: " + e);
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
