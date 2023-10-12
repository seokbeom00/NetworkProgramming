package org.example.datastream;

import java.io.*;

public class example_1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        boolean addTab = false;

        fos = new FileOutputStream("data.bin");
        dos = new DataOutputStream(fos);
        dos.writeBoolean(true);
        if(addTab){
            dos.writeChar('\n');
        }
        dos.writeByte((byte)125);
        if(addTab){
            dos.writeChar('\n');
        }
        dos.writeInt(10);
        if(addTab){
            dos.writeChar('\n');
        }
        dos.writeDouble(200.5);
        if(addTab){
            dos.writeChar('\n');
        }
        dos.writeUTF("hello world는 영어임");
        System.out.println("저장햇습니다.");

        fos.close();
        dos.close();

        FileInputStream fis = null;
        DataInputStream dis = null;

        fis = new FileInputStream("data.bin");
        dis = new DataInputStream(fis);

        boolean boolVar = dis.readBoolean();
        if(addTab){
            dis.readChar();
        }
        byte byteVar = dis.readByte();
        if(addTab){
            dis.readChar();
        }
        int intVar = dis.readInt();
        if(addTab){
            dis.readChar();
        }
        double doublVar = dis.readDouble();
        if(addTab){
            dis.readChar();
        }
        String stringVar = dis.readUTF();

        System.out.println(boolVar);
        System.out.println(byteVar);
        System.out.println(intVar);
        System.out.println(doublVar);
        System.out.println(stringVar);
        fis.close();
        dis.close();
    }
}
