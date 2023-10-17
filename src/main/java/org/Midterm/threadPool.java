package org.Midterm;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class threadPool {
    static class Find extends Thread {
        befoData bf;
        private int max;
        private int min;
        private int start_c;

        public Find(int max, int min, int start_c, befoData bf) {
            this.max = max;
            this.min = min;
            this.start_c = start_c;
            this.bf = bf;
        }

        public void working(String str){
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new URL(str).openStream()), 65536)) {
                String[] nums;
                String line;
                bf.filelFile++;
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
            for (int d = start_c; d < start_c + 5; d++) {
                for (int c = 1; c <= 50; c++) {
                    //http://52.78.37.72:8080/static/Archive/file%20
                    //http://nas.hoony.me:9998/midtermPractice/file%20
                    String url = "http://52.78.37.72:8080/static/Archive/file%20";
                    try {
                        String left = URLEncoder.encode("<", "UTF-8");
                        String right = URLEncoder.encode(">", "UTF-8");
                        working(url
                                + "(c=" + c + ")_(d=" + d + ").txt");
                        working(url
                                + "(c=" + c + ")_%3Cd=" + d +right+".txt");
                        working(url
                                + "%3Cc=" + c +right+"_(d=" + d + ").txt");
                        working(url
                                + "%3Cc=" + c + right+"_%3Cd=" + d + right+".txt");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            bf.Max = max;
            bf.Min = min;
        }
    }
    static class befoData{
        private int Max;
        private int Min;
        private int filelFile = 0;
        private int freq[];
        public befoData(int[] freq){
            this.freq = freq;
        }
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        int threadNum = 10;

        for (int j = 0; j < 5; j++) {
            ExecutorService executor = Executors.newFixedThreadPool(threadNum); // 스레드 풀 생성

            befoData[] bf = new befoData[threadNum];
            for (int i = 0; i < threadNum; i++) {
                bf[i] = new befoData(new int[2001]);
            }

            int max_freq_num = 0, max_freq = 0, min_freq_num = 0, min_freq = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, missingFile = 2500;

            for (int i = 0; i < threadNum; i++) {
                final Find find = new Find(Integer.MIN_VALUE, Integer.MAX_VALUE, i * 5 + 1, bf[i]);
                executor.submit(find); // 스레드 풀에 작업 제출
            }

            executor.shutdown(); // 작업 제출 완료를 알리고 더 이상 작업을 받지 않음

            try {
                // 모든 작업이 완료될 때까지 대기. 실제 사용 시에는 적절한 타임아웃을 설정해야 합니다.
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int[] Max = new int[threadNum];
            int[] Min = new int[threadNum];
            for (int i = 0; i < threadNum; i++) {
                Max[i] = bf[i].Max;
                Min[i] = bf[i].Min;
                missingFile -= bf[i].filelFile;
            }
            int[] freq = new int[2001];
            for (int i = 0; i < threadNum; i++) {
                for (int f = 0; f < 2001; f++) {
                    freq[f] += bf[i].freq[f];
                }
                if (Max[i] > max) {
                    max = Max[i];
                }
                if (Min[i] < min) {
                    min = Min[i];
                }
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
            System.out.println("min_freq_num: " + min_freq_num + " min_freq: " + min_freq);
        }
        long end = System.nanoTime();
        System.out.println("Average Run-time: " + (end - start) / 1000000000/5);
    }
}
