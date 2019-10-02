package com.pack;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class server {

    public static void main(String args[]) throws Exception {
        byte[] receiveData = new byte[100];
        byte[] sendData;
        try (DatagramSocket serverSocket = new DatagramSocket(1111)) {
            while (true)
            {
                int a, b, c;
                final double[] sum1 = {0.0};
                final double[] sum2 = {0.0};

                DatagramPacket receivePacket = new DatagramPacket(receiveData, 100);

                serverSocket.receive(receivePacket);
                String str = new String(receivePacket.getData());
                str = str.substring(0, str.indexOf('\n'));
                System.out.println("number " + str + " recieved as a");
                a = Integer.parseInt(str);

                serverSocket.receive(receivePacket);
                str = new String(receivePacket.getData());
                str = str.substring(0, str.indexOf('\n'));
                System.out.println("number " + str + " recieved as b");
                b = Integer.parseInt(str);

                serverSocket.receive(receivePacket);
                str = new String(receivePacket.getData());
                str = str.substring(0, str.indexOf('\n'));
                System.out.println("number " + str + " recieved as c");
                c = Integer.parseInt(str);

                Thread t1 = new Thread(() ->
                {
                    for (int i = 0; i < b; i++)
                    {
                        sum1[0] += (3 * (double)a) / (2.0 * (double)a + 1.0);
                    }
                });

                Thread t2 = new Thread(() ->
                {
                    for (int j = 0; j < c; j++)
                    {
                        sum2[0] += ((double)b * (double)b + 4.0 * (double)b + 1.0);
                    }
                });
                t1.start();
                t2.start();
                t1.join();
                t2.join();

                String res = String.valueOf(sum1[0] - sum2[0]);
                sendData = res.getBytes();

                System.out.println("result: " + res);

                InetAddress ipAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();


                receivePacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                serverSocket.send(receivePacket);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}





