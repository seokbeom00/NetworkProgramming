package org.example.URI;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {
    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.youtube.com");
            Object o = u.getContent();
            int c;
            InputStream r = (InputStream) o;
            while ((c= r.read())!=-1){
                System.out.println((char) c);
                System.out.println("getto"+o.getClass().getName());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
