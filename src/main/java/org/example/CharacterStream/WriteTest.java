package org.example.CharacterStream;

import java.io.*;

public class WriteTest {
    public static void main(String[] args) throws IOException {
        char[] network= {'N', 'e', 't', 'w', 'o', 'r', 'k'};
        Writer writer = new FileWriter("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/CharacterStream/test.txt");
        writer.write(network, 0, network.length);
    }
}
