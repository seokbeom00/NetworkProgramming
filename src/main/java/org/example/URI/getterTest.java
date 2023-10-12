package org.example.URI;

import java.net.URI;
import java.net.URISyntaxException;


public class getterTest {
    public static void main(String[] args) throws URISyntaxException {
        URI u = new URI("https://www.youtube.com");
        URI u1 = new URI("http://www.youtube.com");
        if(u.equals(u1)){
            System.out.println("asdf");
        }
        else{
            System.out.println(11);
        }
    }
}
