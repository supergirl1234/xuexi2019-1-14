package com;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread14 {

    public static void main(String[] args) {
        Object object=new Object();
        Runnable myrunnable1=new MyRunnable(true,object);
        Runnable myrunnable2=new MyRunnable(false,object);

        Thread thread1=new Thread(myrunnable1,"生产者");
        Thread thread2=new Thread(myrunnable2,"消费者");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        thread2.start();

    }
}

class MyRunnable implements Runnable{


    private  final boolean flag;
    private  final Object object;

    public MyRunnable(boolean flag, Object object) {
        this.flag = flag;
        this.object = object;
    }

    @Override
    public void run() {
        if(flag){
            this.waitMethod();

        }else{

            this.notifyMethod();
        }
    }

    private void waitMethod(){
        synchronized (object){

            System.out.println(Thread.currentThread().getName()+"生产者开始");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"生产者结束");
        }

    }

    private  void notifyMethod(){
        synchronized (object){

            System.out.println(Thread.currentThread().getName()+"消费者开始");
             object.notify();

            System.out.println(Thread.currentThread().getName()+"消费者结束");
        }

    }
}
