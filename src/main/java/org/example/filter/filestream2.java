package org.example.filter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class filestream2 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long start = System.nanoTime();
        try {
            fis = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/filter/test.txt");
            fos = new FileOutputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/filter/test2.txt");
            int readcount = 0;
            byte[] buffer = new byte[512];

            while ((readcount = fis.read(buffer)) != -1) {
                System.out.println(fis.read());
                fos.write(buffer, 0, readcount);
            }
            System.out.println("complete copy");
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
        System.out.println((end-start)/1000.0);
    }
}
