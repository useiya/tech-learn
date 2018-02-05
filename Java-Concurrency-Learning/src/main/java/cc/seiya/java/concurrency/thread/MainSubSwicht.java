package cc.seiya.java.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * 主子线程 切换
 *
 * @author: libo
 * @date: 2018/2/1 16:14
 */
public class MainSubSwicht {

    public static void main(String[] args) {

        MainSubSwicht mss = new MainSubSwicht();
        Thread main = new Thread(new MainThread(mss));
        Thread sub = new Thread(new SubThread(mss));
        main.start();
        sub.start();
    }
}

class MainThread implements Runnable {

    private String name = "main-thread";
    private Object lock;

    public MainThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            for (int i = 1; i < 100; i += 2) {
                System.out.println(name + " print  odd number :" + i);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class SubThread implements Runnable {

    private String name = "sub-thread";
    private Object lock;

    public SubThread(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            for (int i = 2; i < 100; i += 2) {
                try {
                    System.err.println(name + " print even number :" + i);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
            }
        }
    }
}
