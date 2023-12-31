package org.assignment1;

import org.Midterm.practice2023_web;

import java.io.*;
import java.net.URL;

public class practice2020 {
    static class Find extends Thread {
        befoData bf;
        private int max;
        private int min;
        private int findFile;
        private int start_c;
        private int max_count;
        private int min_count;

        public Find(int max, int min, int start_c, befoData bf) {
            this.max = max;
            this.min = min;
            this.findFile = 0;
            this.start_c = start_c;
            this.bf = bf;
        }

        public void working(String str){
            try{
                FileInputStream fi = new FileInputStream(str);
                BufferedReader br = new BufferedReader(new InputStreamReader(fi), 65536);
                String[] nums;
                String line;
                findFile ++;
                while ((line = br.readLine()) != null) {
                    nums = line.split("\\s");
                    for (int i = 0; i < nums.length; i++) {
                        int number = Integer.parseInt(nums[i]);
                        if (number > max) {
                            max = number;
                        }
                        if (number < min) {
                            min = number;
                        }
                        bf.freq[number]++;
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
        public void findColRow(String str, int max, int min, int c, int d){
            try{
                FileInputStream fi = new FileInputStream(str);
                BufferedReader br = new BufferedReader(new InputStreamReader(fi), 65536);
                String[] nums;
                String line;
                int max_row = 1;
                int max_col = 1;
                int min_row = 1;
                int mun_col = 1;
                while ((line = br.readLine()) != null) {
                    nums = line.split("\\s");
                    max_row = 0;
                    for (int i = 0; i < nums.length; i++) {
                        int number = Integer.parseInt(nums[i]);
                        if(number == max){
                            max_count++;
                            System.out.println("c: "+c+" d: "+d+" row: "+max_row+" col: "+max_col+" max: "+max);
                        }
                        if(number == min){
//                            System.out.println("c: "+c+" d: "+d+" row: "+min_row+" col: "+mun_col+" min: "+min);
                            min_count++;
                        }
                        max_row++;
                        min_row++;
                    }
                    max_col++;
                    min_row++;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
        @Override
        public void run() {
            for (int c = start_c; c < start_c + 5; c++) {
                for (int d = 1; d <= 50; d++) {
                    String url = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file ";
                    working(url
                            + "(c=" + c + ")_(d=" + d + ").txt");
                    working(url
                            + "(c=" + c + ")_<d=" + d + ">.txt");
                    working(url
                            + "<c=" + c + ">_(d=" + d + ").txt");
                    working(url
                            + "<c=" + c + ">_<d=" + d + ">.txt");
                }
            }
            bf.Max = max;
            bf.Min = min;
            for (int c = start_c; c < start_c + 5; c++) {
                for (int d = 1; d <= 50; d++) {
                    String url = "/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file ";
                    findColRow(url
                            + "(c=" + c + ")_(d=" + d + ").txt", max, min, c, d);
                    findColRow(url
                            + "(c=" + c + ")_<d=" + d + ">.txt", max, min, c, d);
                    findColRow(url
                            + "<c=" + c + ">_(d=" + d + ").txt", max, min, c, d);
                    findColRow(url
                            + "<c=" + c + ">_<d=" + d + ">.txt", max, min, c, d);
                }
            }
            bf.max_count = max_count;
            bf.min_count = min_count;
        }
    }
    static class befoData{
        private int Max;
        private int Min;
        private int freq[];
        private int max_count;
        private int min_count;
        public befoData(int[] freq){
            this.freq = freq;
        }
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        int threadNum = 10;
        befoData[] bf = new befoData[threadNum];
        for(int j=0; j<1; j++) {
            for (int i = 0; i < threadNum; i++) {
                bf[i] = new befoData(new int[2001]);
            }
            int max_freq_num = 0, max_freq = 0, min_freq_num = 0, min_freq = Integer.MAX_VALUE, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, missingFile = 2500;
            Find[] finds = new Find[threadNum];
            for (int i = 0; i < threadNum; i++) {
                finds[i] = new Find(Integer.MIN_VALUE, Integer.MAX_VALUE, i * 5 + 1, bf[i]);
                finds[i].start();
            }
            for (int i = 0; i < threadNum; i++) {
                try {
                    finds[i].join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int Max[] = new int[threadNum];
            int Min[] = new int[threadNum];
            for(int i=0; i<threadNum; i++){
                Max[i] = bf[i].Max;
                Min[i] = bf[i].Min;
            }
            int freq[] = new int[2001];
            for(int i = 0; i < threadNum; i++) {
                for(int f = 0; f < 2001; f++) {
                    freq[f] += bf[i].freq[f];
                }
                if (Max[i] > max) {
                    max = Max[i];
                }
                if (Min[i] < min) {
                    min = Min[i];
                }
                missingFile -= finds[i].findFile;
            }
            int mc = 0;
            int minc = 0;
            for(int i=0; i<threadNum; i++){
                mc += bf[i].max_count;
                minc += bf[i].min_count;
            }
            System.out.println("max frequency: "+mc);
            System.out.println("min frequency: "+minc);
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > max_freq) {
                    max_freq = freq[i];
                    max_freq_num = i;
                }
                if (freq[i] < min_freq && freq[i] != 0) {
                    min_freq = freq[i];
                    min_freq_num = i;
                }
            }
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
            System.out.println("Missing File: " + missingFile);
            System.out.println("max_freq_num: " + max_freq_num + " max_freq: " + max_freq);
            System.out.println("min_freq_num: " + min_freq_num + " max_freq: " + min_freq);
        }
        long end = System.nanoTime();
        System.out.println("Average Run-time: "+(end - start)/5);
    }
}
