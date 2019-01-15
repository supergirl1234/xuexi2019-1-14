package com;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread22 {

    public static void main(String[] args) {

       /* //固定数量的线程的线程池 newFixedThreadPool
          //适用于：服务器负载不较高，对资源有限制，可以采用固定线程数
        ExecutorService executorService=Executors.newFixedThreadPool(5);
        final AtomicInteger count=new AtomicInteger(0);
        for(int i=0;i<100;i++){

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"="+count.getAndAdd(1));
                }
            });
        }
        executorService.shutdown();
        */


       /*//单线程的线程池
        // 适用于按照顺序执行
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        final AtomicInteger count=new AtomicInteger(0);
        for(int i=0;i<100;i++){

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"="+count.getAndAdd(1));
                }
            });
        }
        executorService.shutdown();
*/

     /*  //缓存线程
        //适用于：任务多，但执行时间短，或者服务器负载比较小
        ExecutorService executorService=Executors.newCachedThreadPool();
        final AtomicInteger count=new AtomicInteger(0);
        for(int i=0;i<100000;i++){

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"="+count.getAndAdd(1));
                }
            });
        }
        executorService.shutdown();

*/


     //任务调度
        ScheduledExecutorService service=Executors.newScheduledThreadPool(5,
                new ThreadFactory() {
            private final AtomicInteger threadId=new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread=new Thread(r);
                        thread.setName("调度任务线程"+threadId.getAndAdd(1));
                        return thread;
                    }
                });

        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个一次性任务"+Thread.currentThread().getName());
            }
        },1,TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个周期性任务A"+Thread.currentThread().getName());
            }

        },1,2,TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个周期性任务B"+Thread.currentThread().getName());
            }

        },1,5,TimeUnit.SECONDS);


    }
}
