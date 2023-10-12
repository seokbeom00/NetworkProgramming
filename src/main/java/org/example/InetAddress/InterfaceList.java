package org.example.InetAddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class InterfaceList {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        NetworkInterface ni = NetworkInterface.getByName("en0");
        if(ni!=null){
            System.out.println(ni);
        }
        Enumeration<InetAddress> addressEnumeration = ni.getInetAddresses();
        for(InetAddress element : Collections.list(addressEnumeration)){
            System.out.println(element);
        }
        InetAddress ia = InetAddress.getByName("127.0.0.1");
        NetworkInterface ni2 = NetworkInterface.getByInetAddress(ia);
        System.out.println(ni2);
        Enumeration<InetAddress> addressEnumeration2 = ni2.getInetAddresses();
        for(InetAddress element : Collections.list(addressEnumeration2)){
            System.out.println(element);
        }
        StringBuilder identify = new StringBuilder();
        try {
            byte[] macBuffer = ni.getHardwareAddress();
            for(int i=0; i<macBuffer.length; i++){
                identify.append(String.format("%02X%s", macBuffer[i],
                        (i<macBuffer.length-1)? "-" : ""));
            }
        }catch (SocketException ex){

        }
    }
}
