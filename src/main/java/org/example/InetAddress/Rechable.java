package org.example.InetAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Rechable {
    public static void main(String[] args) {
        try {
            byte[] addr = {(byte) 202, 30, 38, 108};
            InetAddress address = InetAddress.getByAddress(addr);
            int timeout = 20000;
            int ttl = 10;
            if(address.isReachable(timeout)){
                System.out.println(address.getHostName());
            }else{
                System.out.println("bhb");
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
