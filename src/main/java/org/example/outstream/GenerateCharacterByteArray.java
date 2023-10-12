package org.example.outstream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacterByteArray {
    public static void main(String[] args) {
        try {
            generateCharacters(System.out);
        } catch (IOException ex) {

        }
    }
    public static void generateCharacters(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        int count = 0;
        byte[] data = new byte[numberOfCharactersPerLine + 2];
        while (count < 1000){
            int temp = 0;
            for(int i=start; i<start+ numberOfCharactersPerLine; i++){
                data[temp] = (byte)((i-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter);
                temp++;
            }
            data[numberOfCharactersPerLine] = (byte) '\r';
            data[numberOfCharactersPerLine + 1] = (byte) '\n';
            out.write(data);
            start = ((start+1)-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter;
            count++;
        }
    }
}
