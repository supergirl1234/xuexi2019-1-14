package com;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class Testthread13 {

    public static void main(String[] args) {

        Object object=new Object();
        new Thread(()->{

            synchronized (object){
                System.out.println("唤醒开始");

                object.notify();
            }
        });
        synchronized (object){

            System.out.println("同步开始");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("同步结束");
        }

    }
}
