package com;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author:Fanleilei
 * Created:2019/1/14 0014
 */
public class TestThread23 {

    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行"+System.currentTimeMillis());
            }
        },1000,500);

    }
}
