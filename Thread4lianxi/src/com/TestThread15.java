package com;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread15 {
    public static void main(String[] args) {
        Object object=new Object();
        Runnable myrunnable1=new MyRunnable2(true,object);
        Runnable myrunnable2=new MyRunnable2(true,object);
        Runnable myrunnable3=new MyRunnable2(true,object);
        Runnable myrunnable4=new MyRunnable2(false,object);

        Thread thread1=new Thread(myrunnable1,"生产者A");
        Thread thread2=new Thread(myrunnable2,"生产者B");
        Thread thread3=new Thread(myrunnable3,"生产者C");
        Thread thread4=new Thread(myrunnable4,"消费者");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
class MyRunnable2 implements Runnable{


    private  final boolean flag;
    private  final Object object;

    public MyRunnable2(boolean flag, Object object) {
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
           // object.notify();//只能唤醒一个
             object.notifyAll();//可以多个
            System.out.println(Thread.currentThread().getName()+"消费者结束");
        }

    }
}
