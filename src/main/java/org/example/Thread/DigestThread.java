package org.example.Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestThread extends Thread{
    private String filename;
    public DigestThread(String filename){
        this.filename = filename;
    }
    @Override
    public void run(){
        try {
            FileInputStream in = new FileInputStream(filename);
            //메시지 다이제스트 인스턴스를 만들어 주는거임
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1);
            din.close();
            byte[] digest = sha.digest();
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(toHexString(digest));
            System.out.println(result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
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
        Thread t = new DigestThread("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/Thread/a.txt");
        t.start();
    }
}
