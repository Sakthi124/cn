//tcpchatserver
import java.io.*;
import java.net.*;

class tcpchatserver
{
    public static void main(String args[]) throws Exception
    {
        PrintWriter toClient;
        BufferedReader fromUser, fromClient;

        try
        {
            ServerSocket Srv = new ServerSocket(4000);
            System.out.println("\nServer started...");
            Socket Clt = Srv.accept();
            System.out.println("Client connected\n");

            toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Clt.getOutputStream())), true);
            fromClient = new BufferedReader(new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));

            String CltMsg, SrvMsg;

            while(true)
            {
                CltMsg = fromClient.readLine();

                if(CltMsg.equals("end"))
                    break;

                System.out.println("Client : " + CltMsg);

                System.out.print("Message to Client : ");
                SrvMsg = fromUser.readLine();
                toClient.println(SrvMsg);
            }

            System.out.println("\nClient Disconnected");

            fromClient.close();
            toClient.close();
            fromUser.close();
            Clt.close();
            Srv.close();
        }
        catch (Exception E)
        {
            System.out.println("Error: " + E.getMessage());
        }
    }
}

//tcpchatclient

import java.io.*;
import java.net.*;

class tcpchatclient
{
    public static void main(String args[]) throws Exception
    {
        Socket Clt;
        PrintWriter toServer;
        BufferedReader fromUser, fromServer;

        try
        {
            Clt = new Socket(InetAddress.getLocalHost(), 4000);
            toServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Clt.getOutputStream())), true);
            fromServer = new BufferedReader(new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));

            String CltMsg, SrvMsg;
            System.out.println("Type \"end\" to Quit\n");

            while(true)
            {
                System.out.print("Message to Server : ");
                CltMsg = fromUser.readLine();
                toServer.println(CltMsg);

                if(CltMsg.equals("end"))
                    break;

                SrvMsg = fromServer.readLine();
                System.out.println("Server : " + SrvMsg);
            }

            Clt.close();
        }
        catch(Exception E)
        {
            System.out.println("Error: " + E.getMessage());
        }
    }
}
