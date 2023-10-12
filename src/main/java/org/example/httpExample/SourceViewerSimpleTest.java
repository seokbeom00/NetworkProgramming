package org.example.httpExample;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewerSimpleTest {
    public static void main(String[] args) throws IOException {
        InputStream in  = null;
        try {
            URL u = new URL("http://www.konkuk.ac.kr");
            in = u.openStream();
            int c;
            while((c=in.read())!=-1){
                System.out.write(c);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(in!=null){
                in.close();
            }
        }
    }
}
