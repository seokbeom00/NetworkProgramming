package org.Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MidtermExam2022 {
	public static int arr[] = new int[3000];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;//�� �ݺ�
		long startTime = System.currentTimeMillis();
		for(int k=0;k<n;k++) {
			
			Find[] f = new Find[10];
			for (int i = 0; i < f.length; i++) {
				f[i] = new Find(Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			FindNum t1 = new FindNum(f[0], 1);
			FindNum t2 = new FindNum(f[1], 6);
			FindNum t3 = new FindNum(f[2], 11);
			FindNum t4 = new FindNum(f[3], 16);
			FindNum t5 = new FindNum(f[4], 21);
			FindNum t6 = new FindNum(f[5], 26);
			FindNum t7 = new FindNum(f[6], 31);
			FindNum t8 = new FindNum(f[7], 36);
			FindNum t9 = new FindNum(f[8], 41);
			FindNum t10 = new FindNum(f[9], 46);

			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();
			t7.start();
			t8.start();
			t9.start();
			t10.start();

			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
				t5.join();
				t6.join();
				t7.join();
				t8.join();
				t9.join();
				t10.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			int max_freq_num = 0, max_freq = 0, min_freq_num = 0, min_freq = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int miss = 0;
			for (int i = 0; i < f.length; i++) {
				if (max < f[i].max) {
					max = f[i].max;
				}
				if (min > f[i].min) {
					min = f[i].min;
				}
				miss += f[i].missingFile;
			}
			for (int i = 0; i < 2000; i++) {
				if (max_freq < arr[i]) {
					max_freq = arr[i];
					max_freq_num = i;
				}
				if (min_freq > arr[i] && arr[i] != 0) {
					min_freq = arr[i];
					min_freq_num = i;
				}
			}

			
			System.out.println("2.(a) max_freq_num = " + max_freq_num + ", max_freq = " + max_freq);
			System.out.println("2.(b) min_freq_num = " + min_freq_num + ", min_freq = " + min_freq);
			System.out.println("2.(c) max = " + max + ", min = " + min);
			System.out.println("2.(d) number of missing files = " + miss);
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Run-time: " + (endTime - startTime)/n / 1000 + "s");
		
	}

	static class Find {
		int max, min;
		int missingFile;

		public Find(int max, int min) {
			this.max = max;
			this.min = min;
		}

	}

	static class FindNum extends Thread {
		Find f;
		int start;

		public FindNum(Find f, int start) {
			this.f = f;
			this.start = start;
		}

		public synchronized void arrPlus(int num) {
			arr[num]++;
		}

		@Override
		public void run() {
			for (int d = start; d <= start + 4; d++) {
				for (int c = 1; c <= 50; c++) {
					String url = "http://localhost:4000/download/" + "file%20(c=" + c
							+ ")%20(d=" + d + ").txt";
					try {
						URL u = new URL(url);
						InputStream in = u.openStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String line = "";
						while ((line = br.readLine()) != null) {
							String[] nums = line.split("\\s+");
							for (int i = 0; i < nums.length; i++) {
								int num = Integer.parseInt(nums[i]);
								arrPlus(num);
								if (f.max < num) {
									f.max = num;
								}
								if (f.min > num) {
									f.min = num;
								}
							}
						}

					} catch (MalformedURLException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {

						try {
							url = "http://localhost:4000/download/" + "file%20(c=" + c
									+ ")%20(d=" + d + ").txt";
							URL u = new URL(url);
							InputStream in = u.openStream();
							BufferedReader br = new BufferedReader(new InputStreamReader(in));
							String line = "";
							while ((line = br.readLine()) != null) {
								String[] nums = line.split("\\s+");
								for (int i = 0; i < nums.length; i++) {
									int num = Integer.parseInt(nums[i]);
									arrPlus(num);
									if (f.max < num) {
										f.max = num;
									}
									if (f.min > num) {
										f.min = num;
									}
								}
							}
						} catch (MalformedURLException e1) {
							throw new RuntimeException(e1);
						} catch (IOException e2) {
							try {
								url = "http://localhost:4000/download/" + "file%20(c=" + c
										+ ")%20(d=" + d + ").txt";
								URL u = new URL(url);
								InputStream in = u.openStream();
								BufferedReader br = new BufferedReader(new InputStreamReader(in));
								String line = "";
								while ((line = br.readLine()) != null) {
									String[] nums = line.split("\\s+");
									for (int i = 0; i < nums.length; i++) {
										int num = Integer.parseInt(nums[i]);
										arrPlus(num);
										if (f.max < num) {
											f.max = num;
										}
										if (f.min > num) {
											f.min = num;
										}
									}
								}
							} catch (MalformedURLException e3) {
								throw new RuntimeException(e3);
							} catch (IOException e4) {
								try {
									url = "http://localhost:4000/download/" + "file%20(c=" + c
											+ ")%20(d=" + d + ").txt";
									URL u = new URL(url);
									InputStream in = u.openStream();
									BufferedReader br = new BufferedReader(new InputStreamReader(in));
									String line = "";
									while ((line = br.readLine()) != null) {
										String[] nums = line.split("\\s+");
										for (int i = 0; i < nums.length; i++) {
											int num = Integer.parseInt(nums[i]);
											arrPlus(num);
											if (f.max < num) {
												f.max = num;
											}
											if (f.min > num) {
												f.min = num;
											}
										}
									}
								} catch (MalformedURLException e3) {
									throw new RuntimeException(e3);
								} catch (IOException e5) {
									f.missingFile++;
								}
							}
						}
					}
				}

			}
		}
	}
}
