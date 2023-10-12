package org.example.InetAddress;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.*;

public class AddressResolutionByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.konkuk.ac.kr");
            System.out.println(address);

            InetAddress address1 = InetAddress.getByName("202.30.38.108");
            System.out.println(address1.getHostName());

            InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
            for(InetAddress address2 : addresses){
                System.out.println(address2);
            }
            byte[] address5 = {107, 23, (byte) 216, (byte) 196};
            InetAddress ia = InetAddress.getByAddress(address5);
            InetAddress iaName = InetAddress.getByAddress("named.com", address5);
            System.out.println(iaName);

            System.out.println(address1.getHostAddress());
            System.out.println(address1.getCanonicalHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
