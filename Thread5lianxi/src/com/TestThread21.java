package com;

import java.util.concurrent.Executor;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread21 implements Executor {


    @Override
    public void execute(Runnable command) {
        new Thread(command).start();

    }
    public static void main(String[] args) {
        Executor executor=new TestThread21();
        executor.execute(()->System.out.println("hello"));
        executor.execute(()->System.out.println("hello"));
        executor.execute(()->System.out.println("hello"));
        executor.execute(()->System.out.println("hello"));

    }
}
