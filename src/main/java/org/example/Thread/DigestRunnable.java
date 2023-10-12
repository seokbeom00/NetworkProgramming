package org.example.Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class DigestRunnable implements Runnable{
    private String filename;
    private byte[] digest;
    public DigestRunnable(String filename){
        this.filename = filename;
    }
    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            //메시지 다이제스트 인스턴스를 만들어 주는거임
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1);
            din.close();
            digest = sha.digest();
            CallbackDigest.CallbackDigestUserInterface.receiveDigest(digest, filename);
//            StringBuilder result = new StringBuilder(filename);
//            result.append(": ");
//            result.append(toHexString(digest));
//            System.out.println(result);
//            System.out.print(filename+": ");
//            System.out.print(toHexString(digest));
//            System.out.println();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] getDigest(){
        return digest;
    }
    public static String toHexString(byte[] digest){
        StringBuilder hexString = new StringBuilder();
        for(int i=0; i<digest.length; i++){
            String hex = Integer.toHexString(0xFF & digest[i]);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String[] list = new String[2];
        list[0] = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/Thread/a.txt";
        list[1] = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/Thread/b.txt";

        for(int i =0; i<2; i++){
            CallbackDigest cb = new CallbackDigest(list[i]);
            Thread t = new Thread(cb);
            t.start();
        }
    }
}
