package org.example.URI;

public class QuerySringUsage {
    public static void main(String[] args) {
        QueryString qs = new QueryString();
        qs.add("hl", "en");
        qs.add("as_q", "Ja va");
        qs.add("as_eqq", "I/O");
        String url = "https://www.google.com/search?"+qs;
        System.out.println(url);
    }
}
