package org.example.URI;

import java.net.URI;
import java.net.URISyntaxException;

public class UriConstructorTest {
    public static void main(String[] args) {
        try{
            URI voice = new URI("callme+82-2-450-1234");
            URI web = new URI(("http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hdc"));
            URI book = new URI(":isbn:1-565-92870-9");

            System.out.println(voice);
            System.out.println(web);
            System.out.println(book);
        }catch (URISyntaxException ex){
            System.out.println(ex);
        }
    }
}
