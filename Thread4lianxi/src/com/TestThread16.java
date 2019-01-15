package com;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread16 {

    public static void main(String[] args) {

        Queue<Goods> queue=new LinkedList<>();
        AtomicInteger goodsName=new AtomicInteger(1);
        Object monitor=new Object();

        Producer producer1=new Producer(queue,monitor,goodsName);
        Producer producer2=new Producer(queue,monitor,goodsName);
        Producer producer3=new Producer(queue,monitor,goodsName);
        Consumer customer1=new Consumer(queue,monitor);

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
class Goods{

    private String name;

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                '}';
    }
}
//生产者
class Producer implements Runnable{
    private final Queue<Goods> goods;
    private final Object monitor;//监视器
    private  final AtomicInteger atomicInteger;//这是一个原子操作

    public Producer(Queue<Goods> goods, Object monitor, AtomicInteger atomicInteger) {
        this.goods = goods;
        this.monitor = monitor;
        this.atomicInteger = atomicInteger;
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

                    Goods good = new Goods(String.valueOf(atomicInteger.getAndAdd(1)));
                    goods.add(good);
                    System.out.println(Thread.currentThread().getName() + "生产" + good);
                }

            }
        }

    }
}
//消费者

class Consumer implements Runnable{
    private final Queue<Goods> goods;
    private final Object monitor;

    public Consumer(Queue<Goods> goods, Object monitor) {
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

                     Goods good=goods.poll();
                      System.out.println("消费者消费"+good);
                  }
              }


          }
    }
}
