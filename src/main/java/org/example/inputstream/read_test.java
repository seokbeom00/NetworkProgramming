package org.example.inputstream;

import java.io.IOException;
import java.io.InputStreamReader;

public class read_test {
    public static void main(String[] args) throws IOException {
//        int inChar = 0;
//        int inChar2 = 0;
//        byte[] ch = {'q', 'w', 'e'};
//        System.out.println("Enter a Character");
//        int i = 0;
//        inChar = System.in.read();
//        System.out.write(inChar);
//        System.out.println();
//        System.out.println(inChar);
//        int input = System.in.read();
//        System.out.println(input);
//        System.out.write(input);
//        System.out.flush();
        long byteSkipped = 0;
        long byteToSkip = 80;
        while(byteSkipped < byteToSkip){
            long n = System.in.skip(byteToSkip - byteSkipped);
            if(n == -1){
                break;
            }
            byteSkipped += n;
        }
    }
}

