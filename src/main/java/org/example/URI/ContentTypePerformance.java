package org.example.URI;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentTypePerformance {
    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.youtube.com");
            Class<?>[] types = new Class[3];
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            Object o = u.getContent(types);
            if(o instanceof String){
                System.out.println(o);
            } else if (o instanceof Reader) {
                int c;
                Reader r = (Reader) o;
                while ((c = r.read())!=-1){
                    System.out.println((char)c);
                }
                r.close();
            }else if(o instanceof InputStream){
                int c;
                InputStream in = (InputStream) o;
                while ((c = in.read())!=-1){
                    System.out.println((char)c);
                }
                in.close();
            }else {
                System.out.println("오류남");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
