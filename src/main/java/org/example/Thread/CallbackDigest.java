package org.example.Thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackDigest implements Runnable{
    private String filename;
    public CallbackDigest(String filename){
        this.filename = filename;
    }

    @Override
    public void run() {
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read() != -1);
            din.close();
            byte[] digest = sha.digest();
            CallbackDigestUserInterface.receiveDigest(digest, filename);
        }catch (IOException ex){
            System.out.println(ex);
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex);
        }
    }
    public class CallbackDigestUserInterface {
        public static String toHexString(byte[] bytes) {
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }

        public static void receiveDigest(byte[] digest, String name) {
            StringBuilder result = new StringBuilder(name);
            result.append(": ");
            result.append(toHexString(digest));
            System.out.println(result);
        }
    }
}
