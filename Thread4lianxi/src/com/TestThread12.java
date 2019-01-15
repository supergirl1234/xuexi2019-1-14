package com;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread12 {

    public static void main(String[] args) {
        for(int i=0;i<3;i++){

            Thread t=new MyThread();
            t.setName("Thread"+i);
            t.start();
        }

    }
}

class Sync{

    public void test(){

        synchronized (Sync.class){//全局锁

            System.out.println("Sync对象的Test方法执行开始"+Thread.currentThread().getName() );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sync对象的Test方法执行结束"+Thread.currentThread().getName() );

        }

    }
}
class MyThread extends Thread{

    @Override
    public void run() {
        Sync sync=new Sync();
        sync.test();
    }
}