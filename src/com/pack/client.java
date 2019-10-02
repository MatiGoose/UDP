package com.pack;

import java.io.*;
import java.net.*;

public class client
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        DatagramPacket sendPacket;



        int arr[] = new int [3];
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print("Введите элемент arr["+ i +"]:");
            System.in.read(sendData);
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1111 );
            clientSocket.send(sendPacket);
        }

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        System.out.println("the answer is "+new String(receivePacket.getData()));
        clientSocket.close();


    }
}
