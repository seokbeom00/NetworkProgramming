package org.Midterm;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class practice2023 {
    public static AtomicInteger freq[] = new AtomicInteger[2000];
    public static int Max[] = new int[10];
    public static int Min[] = new int[10];
    public static int FileNum[] = new int[10];

    static class Find extends Thread {
        private int max;
        private int min;
        private int fileNum;
        private int start_c;

        public Find(int max, int min, int start_c) {
            this.max = max;
            this.min = min;
            this.fileNum = 0;
            this.start_c = start_c;
        }

        public void addFreq(int num) {
            freq[num].incrementAndGet();
        }
        public void addMax(int threadNum, int num){
            Max[threadNum] = num;
        }
        public void addMin(int threadNum, int num){
            Min[threadNum] = num;
        }
        public void addFileNum(int threadNum, int num){
            FileNum[threadNum] = num;
        }

        @Override
        public void run() {
            for (int c = start_c; c < start_c + 5; c++) {
                for (int d = 1; d < 51; d++) {
                    try {
                        FileInputStream fi = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                                + "(c=" + c + ")_(d=" + d + ").txt");
                        BufferedReader br = new BufferedReader(new InputStreamReader(fi), 32768);
                        String[] nums;
                        String line;
                        fileNum += 1;
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
                                addFreq(number);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        try {
                            FileInputStream fi = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                                    + "(c=" + c + ")_<d=" + d + ">.txt");
                            BufferedReader br = new BufferedReader(new InputStreamReader(fi), 32768);
                            String[] nums;
                            String line;
                            fileNum += 1;
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
                                    addFreq(number);
                                }
                            }
                        } catch (FileNotFoundException e1) {
                            try {
                                FileInputStream fi = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                                        + "<c=" + c + ">_(d=" + d + ").txt");
                                BufferedReader br = new BufferedReader(new InputStreamReader(fi), 32768);
                                String[] nums;
                                String line;
                                fileNum += 1;
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
                                        addFreq(number);
                                    }
                                }
                            } catch (FileNotFoundException e2) {
                                try {
                                    FileInputStream fi = new FileInputStream("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/Midterm/Archive/file "
                                            + "<c=" + c + ">_<d=" + d + ">.txt");
                                    BufferedReader br = new BufferedReader(new InputStreamReader(fi), 32768);
                                    String[] nums;
                                    String line;
                                    fileNum += 1;
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
                                            addFreq(number);
                                        }
                                    }
                                } catch (FileNotFoundException e3) {
                                    //없는 파일 처리?
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            addMax((start_c-1)/5, max);
            addMin((start_c-1)/5, min);
            addFileNum((start_c-1)/5, fileNum);
        }
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        for(int j=0; j<20; j++) {
//            Arrays.fill(freq, 0);
            for (int i = 0; i < freq.length; i++) {
                freq[i] = new AtomicInteger();
            }
            for (int i = 0; i < Max.length; i++) {
                Max[i] =0;
            }
            for (int i = 0; i < Min.length; i++) {
                Min[i] = 0;
            }
            for (int i = 0; i < FileNum.length; i++) {
                FileNum[i] = 0;
            }
            int threadNum = 10;
            int max_freq_num = 0, max_freq = 0, min_freq_num = 0, min_freq = Integer.MAX_VALUE, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, missingFile = 2500;
            Find[] finds = new Find[threadNum];
            for (int i = 0; i < threadNum; i++) {
                finds[i] = new Find(Integer.MIN_VALUE, Integer.MAX_VALUE, i * 5 + 1);
                finds[i].start();
            }
            for (int i = 0; i < threadNum; i++) {
                try {
                    finds[i].join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (Max[i] > max) {
                    max = Max[i];
                }
                if (Min[i] < min) {
                    min = Min[i];
                }
                missingFile -= FileNum[i];
            }
            for (int i = 0; i < freq.length; i++) {
                if (freq[i].get() > max_freq) {
                    max_freq = freq[i].get();
                    max_freq_num = i;
                }
                if (freq[i].get() < min_freq && freq[i].get() != 0) {
                    min_freq = freq[i].get();
                    min_freq_num = i;
                }
            }
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
            System.out.println("Missing File: " + missingFile);
            System.out.println("max_freq_num File: " + max_freq_num + " max_freq: " + max_freq);
            System.out.println("min_freq_num File: " + min_freq_num + " max_freq: " + min_freq);
        }
        long end = System.nanoTime();
        System.out.println("Average Run-time: "+(end - start)/5);
    }
}
