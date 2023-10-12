package org.example.bufferdstream;

import java.io.*;

public class Buffering {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/bufferdstream/a.txt");
            fos = new FileOutputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/bufferdstream/b.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int readcount = 0;
            byte[] buffer = new byte[512];
            while ((readcount=bis.read(buffer)) != -1){
                bos.write(buffer, 0, readcount);
            }
            bos.flush();
            System.out.println("복사 완료");
        }catch (IOException ex){
        }
    }
}
