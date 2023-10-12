package org.Midterm;

import java.io.*;
import java.net.URL;

public class tes {
    public static void main(String[] args) throws IOException {
        String url = "http://nas.hoony.me:9998/midtermPractice/file%20(c=1)_(d=26).txt";
        URL u = new URL(url);
        InputStream in = u.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] nums = line.split("\\s+");
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }
        }

    }
}
