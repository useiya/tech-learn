package cc.seiya.java.concurrency;

import cc.seiya.java.concurrency.thread.MyRunnable;
import cc.seiya.java.concurrency.thread.MyThreadByThread;

/**
 * @author: libo
 * @date: 2018/1/31 14:23
 */
public class ThreadMain {

    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
//            MyThreadByThread thread = new MyThreadByThread(i);
            MyRunnable runnable = new MyRunnable(i);
            Thread thread = new Thread(runnable);
            thread.start();
            System.out.println("thread info :" + thread.getName() + "," + thread.getId());
        }
    }

}
