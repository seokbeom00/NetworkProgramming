package org.example.outstream;

import java.awt.*;
import java.io.*;

public class OutputStreamWriteTest {
    public static void main(String[] args) {
        //Auto-generated method stub
        InputStream in = System.in;
        OutputStream out = System.out;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            System.out.println(line);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
