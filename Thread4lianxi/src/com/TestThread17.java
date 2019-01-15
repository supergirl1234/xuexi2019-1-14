package com;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread17 {

    public static void main(String[] args) {

        Queue<Goods1> queue=new LinkedList<>();

        Object monitor=new Object();

        Producer1 producer1=new Producer1(queue,monitor);
        Producer1 producer2=new Producer1(queue,monitor);
        Producer1 producer3=new Producer1(queue,monitor);
        Consumer1 customer1=new Consumer1(queue,monitor);

        Thread thread1=new Thread(producer1,"生产者1");
        Thread thread2=new Thread(producer2,"生产者2");
        Thread thread3=new Thread(producer3,"生产者3");
        Thread thread=new Thread(customer1,"消费者1");


        thread1.start();
        thread2.start();
        thread3.start();
        thread.start();


    }
}

//商品类
class Goods1{

    private String name;

    public Goods1(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods1{" +
                "name='" + name + '\'' +
                '}';
    }
}
//生产者
class Producer1 implements Runnable{
    private final Queue<Goods1> goods;
    private final Object monitor;//监视器


    public Producer1(Queue<Goods1> goods, Object monitor) {
        this.goods = goods;
        this.monitor = monitor;

    }


    @Override
    public void run() {
        while(true) {
            synchronized (monitor) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (goods.size() == 10) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {

                    Goods1 good = new Goods1("产品");
                    goods.add(good);
                    System.out.println(Thread.currentThread().getName() + "生产" + good);
                }

            }
        }

    }
}
//消费者

class Consumer1 implements Runnable{
    private final Queue<Goods1> goods;
    private final Object monitor;

    public Consumer1(Queue<Goods1> goods, Object monitor) {
        this.goods = goods;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            synchronized (monitor){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(goods.isEmpty()){

                    monitor.notifyAll();
                }else{

                    Goods1 good=goods.poll();
                    System.out.println("消费者消费"+good);
                }
            }


        }
    }
}

