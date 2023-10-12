package org.example.bufferdstream;

import java.io.*;

public class Bufferedstream_1 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long start = System.nanoTime();
        try {
            fis = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/filter/test.txt");
            fos = new FileOutputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/filter/test2.txt");

            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readcount = 0;
            byte[] buffer = new byte[512];

            while ((readcount = bis.read()) != -1) {
                System.out.println(bis.read());
                bos.write(buffer, 0, readcount);
            }
            System.out.println("complete copy");
            bis.close();
            bos.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
            }
            try {
                fos.close();
            } catch (IOException e) {
            }
        }
        long end = System.nanoTime();
        System.out.println((end-start));
    }
}
