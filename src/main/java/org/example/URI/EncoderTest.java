package org.example.URI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderTest {
    public static void main(String[] args) {
        try{
            System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
            System. out. println(URLEncoder.encode("This*string*has*asterisks","UTF-8"));
            System.out.println(URLEncoder.encode("This%string%has%percent%signs", "UTF-8"));
            System.out. println(URLEncoder.encode("This+string+hs+pluses", "UTF-8"));
            System. out.println(URLEncoder.encode("This/string/has/slashes","UTF-8"));
            System.out.println(URLEncoder.encode("This:string:has:quotel", "UTF-8"));
        }catch(UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }
}
