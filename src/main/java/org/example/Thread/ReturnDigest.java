package org.example.Thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread{
    private String filename;
    private byte[] digest;
    public ReturnDigest(String filename){
        this.filename = filename;
    }
    @Override
    public void run(){
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read()!=-1);
            din.close();
            digest = sha.digest();
            //good
        }catch (IOException ex){
            System.err.println(ex);
        }catch (NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
    }
    public byte[] getDigest(){
        return digest;
    }
    public static String toHexString(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for(int i=0; i<bytes.length; i++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String[] arr = new String[2];
        arr[0] = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/Thread/a.txt";
        arr[1] = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/Thread/b.txt";

        for(String filename : arr){ //각 파일에 대해서 쓰레드를 각각 생성
            ReturnDigest dr = new ReturnDigest(filename);
            dr.start();
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            byte[] digest = dr.getDigest();
            result.append(toHexString(digest));
            System.out.println(result);
        }
    }
}
