package org.example;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadMaxFinder {
    public static int max(int[] data) throws ExecutionException, InterruptedException {
        if(data.length == 1){
            return data[0];
        }else if(data.length == 0){
            throw new IllegalArgumentException();
        }
        //탐색 구간 반절씩 가져가 주면서
        CallableTest ct1 = new CallableTest(data, 0, data.length/2);
        CallableTest ct2 = new CallableTest(data, data.length/2, data.length);
        //Thread 2개를 spawn 해준거임
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(ct1);
        Future<Integer> future2 = service.submit(ct2);

        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] list = {1, 34, 59, 12, 3};
        int result = MultiThreadMaxFinder.max(list);
        System.out.println(result);
    }

}
