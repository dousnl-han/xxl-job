package com.xxl.job.exectors;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/20 10:40
 */
public class ExectorsTest {

    public static void main(String[] args) throws InterruptedException {
          ExecutorService executorService =  Executors.newFixedThreadPool(3);
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
//        ExecutorService executorService2 = Executors.newWorkStealingPool();
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("thread start ..." + Thread.currentThread().getName());
//                }
//            });
        ThreadPoolExecutor linkedQueueExecutor = new ThreadPoolExecutor(
                3,
                5,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"xxl-job thread " + r.hashCode());
                    }
                }
        );
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            linkedQueueExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("xxl-job thread start ..." + Thread.currentThread().getName());
                }
            });
        }
        //executorService.shutdown();
        linkedQueueExecutor.shutdown();
    }
}
