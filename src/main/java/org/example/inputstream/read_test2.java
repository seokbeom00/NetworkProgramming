package org.example.inputstream;

import java.io.IOException;

import static java.lang.System.in;

public class read_test2 {
    public static void main(String[] args) throws IOException {
        int bytesRead = 0;
        int bytesToRead = 1024;
        byte[] input = new byte[bytesToRead];
        while(bytesRead < bytesToRead){
            bytesRead += System.in.read(input, bytesRead, bytesToRead - bytesRead);
        }
    }
}
