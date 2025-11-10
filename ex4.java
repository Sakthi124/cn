//dnsserver

import java.io.*;
import java.net.*;

public class dnsserver
{
    private static int indexOf(String[] array, String str)
    {
        str = str.trim();
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equalsIgnoreCase(str))
                return i;
        }
        return -1;
    }

    public static void main(String arg[]) throws IOException
    {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ip = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};

        DatagramSocket serversocket = new DatagramSocket(8080); 
        System.out.println("DNS Server Running on Port 8080 (Press Ctrl + C to Quit)");

        while (true)
        {
            byte[] receivedata = new byte[1024];
            DatagramPacket recvpack = new DatagramPacket(receivedata, receivedata.length);
            serversocket.receive(recvpack);

            String hostname = new String(recvpack.getData()).trim();
            System.out.println("Request Received for Host: " + hostname);

            int index = indexOf(hosts, hostname);
            String response = (index != -1) ? ip[index] : "Host Not Found";

            byte[] senddata = response.getBytes();
            InetAddress clientAddress = recvpack.getAddress();
            int clientPort = recvpack.getPort();

            DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, clientAddress, clientPort);
            serversocket.send(sendpack);
        }
    }
}

//dnsclient

import java.io.*;
import java.net.*;

public class dnsclient
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientsocket = new DatagramSocket();

        InetAddress ipaddress;
        if (args.length == 0)
            ipaddress = InetAddress.getLocalHost();
        else
            ipaddress = InetAddress.getByName(args[0]);

        System.out.print("Enter the hostname : ");
        String sentence = br.readLine();

        byte[] senddata = sentence.getBytes();
        DatagramPacket pack = new DatagramPacket(senddata, senddata.length, ipaddress, 8080);
        clientsocket.send(pack);

        byte[] receivedata = new byte[1024];
        DatagramPacket recvpack = new DatagramPacket(receivedata, receivedata.length);
        clientsocket.receive(recvpack);

        String result = new String(recvpack.getData()).trim();
        System.out.println("IP Address: " + result);

        clientsocket.close();
    }
}

