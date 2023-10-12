package org.example.CharacterStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/CharacterStream/test.txt");
            fw = new FileWriter("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/CharacterStream/out.txt");
            char[] buffer = new char[512];
            int count = 0;
            while ((count = fr.read(buffer))!= -1)
            {
                fw.write(buffer, 0, count);
            }
            System.out.println("copy done");
            fr.close();
            fw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
