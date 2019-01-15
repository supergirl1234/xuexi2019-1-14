package com;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread20 {

    public static void main(String[] args) {
/*
       //通过自定义的方式创建线程池执行器
        ThreadPoolExecutor executor1=new ThreadPoolExecutor(
                5,10,1,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        for(int i=0;i<10;i++){
         //execute()方法用于提交不需要返回值的任务
            executor1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });

        }*/


       ThreadPoolExecutor executor2=new ThreadPoolExecutor(
               5, 10, 1, TimeUnit.SECONDS,
               new ArrayBlockingQueue<>(15),
               new ThreadFactory() {
                   private final AtomicInteger threadId = new AtomicInteger(1);

                    //把创建线程的逻辑交给用户
                   @Override
                   public Thread newThread(Runnable r) {
                       Thread t = new Thread(r);
                       t.setName("自定义线程"+String.valueOf(threadId.getAndAdd(1)));
                       return t;
                   }

               }
       );

      /* for(int i=0;i<10;i++){
           //submit()方法用于提交需要返回值的任务
           executor2.submit(new Runnable() {
               @Override
               public void run() {
                   System.out.println(Thread.currentThread().getName());
               }
           });

       }*/
        Future<Integer> future=executor2.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //1.shutdownNow：平滑关闭线程池
        //2.shutdown：非平滑关闭线程池，有可能正在执行的任务没有完成
        //executor2.shutdown();
        executor2.shutdownNow();
        System.out.println(executor2.isShutdown());
        System.out.println(executor2.isTerminated());


    }
}
