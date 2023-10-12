package org.example.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class read_test3 {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;
        try {
            int input = in.read();
            System.out.println(input);
            out.write(input);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
