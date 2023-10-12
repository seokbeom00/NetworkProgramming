package org.example.filter;

import java.io.FileInputStream;
import java.io.IOException;

public class filestream {
    public static void main(String[] args) {
        FileInputStream fis = null;
        long start = System.nanoTime();
        try{
            fis = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/ChildTest.java");
            byte[] buffer = new byte[512];
            int i = 0;
            while ((i =fis.read(buffer)) != -1){
                System.out.write(i);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            try {
                fis.close();
            }catch (IOException e){

            }
        }
        long end = System.nanoTime();
        System.out.println("Run-time: "+(end-start)/1000.0);
    }
}
