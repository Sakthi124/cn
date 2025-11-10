//Serverarp


import java.io.*;
import java.net.*;

class Serverarp
{
    public static void main(String args[])
    {
        try
        {
            ServerSocket server = new ServerSocket(139);
            System.out.println("ARP Server Started...");

            Socket socket = server.accept();
            System.out.println("Client Connected\n");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            String ip[] = {"165.165.80.80","165.165.79.1"};
            String mac[] = {"6A:08:AA:C2","8A:BC:E3:FA"};

            while(true)
            {
                String request = din.readLine(); // Client IP
                if(request == null) break;

                System.out.println("Request Received for IP: " + request);

                String result = "MAC Address Not Found";

                for(int i = 0; i < ip.length; i++)
                {
                    if(request.equals(ip[i]))
                    {
                        result = mac[i];
                        break;
                    }
                }

                dout.writeBytes(result + "\n");
                System.out.println("Replied with MAC: " + result + "\n");
            }

            din.close();
            dout.close();
            socket.close();
            server.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}


//Clientarp

import java.io.*;
import java.net.*;

class Clientarp
{
    public static void main(String args[])
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Socket clsct = new Socket("127.0.0.1",139);

            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());

            System.out.print("Enter the Logical address (IP): ");
            String ip = in.readLine();

            dout.writeBytes(ip + "\n");

            String mac = din.readLine();
            System.out.println("The Physical Address is: " + mac);

            clsct.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
