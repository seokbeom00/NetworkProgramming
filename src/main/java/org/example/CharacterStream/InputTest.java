package org.example.CharacterStream;

import java.io.*;

public class InputTest {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try{
            fis = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/CharacterStream/test.txt");
            isr = new InputStreamReader(fis);
            osw = new OutputStreamWriter(System.out);
            int count = 0;
            char[] buffer = new char[512];
            while ((count=isr.read(buffer))!=-1){
                osw.write(buffer, 0, count);
            }
            fis.close();
            isr.close();
            osw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
