package org.assignment1;


import java.io.*;

public class practice2023 {
    public static int Max[] = new int[10];
    public static int Min[] = new int[10];

    static class Find extends Thread {
        befoData bf;
        private int max;
        private int min;
        private int findFile;
        private int start_c;

        public Find(int max, int min, int start_c, befoData bf) {
            this.max = max;
            this.min = min;
            this.findFile = 0;
            this.start_c = start_c;
            this.bf = bf;
        }

        public void addMax(int threadNum, int num){
            synchronized (Max){
                Max[threadNum] = num;
            }
        }
        public void addMin(int threadNum, int num){
            synchronized (Min){
                Min[threadNum] = num;
            }
        }
        public void working(String str){
            try {
                FileInputStream fi = new FileInputStream(str);
                BufferedReader br = new BufferedReader(new InputStreamReader(fi), 65536);
                findFile++;
                String[] nums;
                String line;
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

        @Override
        public void run() {
            for (int c = start_c; c < start_c + 5; c++) {
                for (int d = 1; d <= 50; d++) {
                    working("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                            + "(c=" + c + ")_(d=" + d + ").txt");
                    working("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                            + "(c=" + c + ")_<d=" + d + ">.txt");
                    working("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                            + "<c=" + c + ">_(d=" + d + ").txt");
                    working("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                            + "<c=" + c + ">_<d=" + d + ">.txt");
                }
            }
            addMax((start_c-1)/5, max);
            addMin((start_c-1)/5, min);
        }
    }
    static class befoData{
        private int freq[];
        public befoData(int freq[]){
            this.freq = freq;
        }
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        int threadNum = 10;
        befoData[] bf = new befoData[threadNum];
        for(int j=0; j<5; j++) {
            for (int i = 0; i < threadNum; i++) {
                bf[i] = new befoData(new int[2001]);
            }
            for (int i = 0; i < Max.length; i++) {
                Max[i] =0;
            }
            for (int i = 0; i < Min.length; i++) {
                Min[i] = 0;
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
