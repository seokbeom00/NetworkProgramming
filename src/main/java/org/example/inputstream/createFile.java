package org.example.inputstream;

import java.io.*;

public class createFile {
    public static void main(String[] args) {

    }
    public void cf(InputStream fis, String outputfile) throws FileNotFoundException {
        //인자를 통해서 범용성 증가 InputStream의 하위 클래스는 전부 받을 수 있게끔
        FileOutputStream fos = new FileOutputStream(outputfile);
        InputStream is = fis;
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //버퍼를 통해서 I/O 효율 증가
        byte[] buffer = new byte[512];
        try{
            int b;
            while ((b=bis.read(buffer))!=-1){
                bos.write(buffer, 0, b);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
