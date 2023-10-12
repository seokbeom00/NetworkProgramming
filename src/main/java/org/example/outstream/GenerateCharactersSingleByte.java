package org.example.outstream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharactersSingleByte {
    public static void main(String[] args) {
        try{
            generateCharacters(System.out);
        }catch (IOException ex){

        }
    }

    public static void generateCharacters(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;//출력할 수 있는 종류 수
        int numberOfCharactersPerLine = 72; //한줄에 이만큼씩 뽑겠다
        int start = firstPrintableCharacter;
        int count = 0;

        while (count < 10){
            for(int i=start; i<start+ numberOfCharactersPerLine; i++){
                out.write((byte)((i-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter));
            }
            out.write((byte)'\r');
            out.write((byte)'\n');
            start = ((start+1)-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter;
            count++;
        }
    }
}