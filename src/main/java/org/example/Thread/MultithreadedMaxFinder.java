package org.example.Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedMaxFinder extends FindMaxTask {
    MultithreadedMaxFinder(int[] data, int start, int end) {
        super(data, start, end);
    }

    public static int max(int[] data) throws ExecutionException, InterruptedException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length/2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);
        service.shutdown();
        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] data = {1,3,5,1,123,14,634,345,2312,4,642,34,234,1,4};
        int result = max(data);
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(result);
    }
}
