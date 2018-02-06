package cc.seiya.java.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * 主线程执行一会儿，子线程执行
 *
 * @author libo
 * @date 2018/1/31  23:19
 */
public class ThreadAlternation {

    public static void main(String[] args) throws InterruptedException {
        ThreadAlternation ta = new ThreadAlternation();
        Thread main = new Thread(new MainThread(ta));
        Thread sub = new Thread(new SubThread(ta));
        main.start();
        sub.start();

//        TimeUnit.MILLISECONDS.sleep(1000);
    }

    /**
     * main thread print odd number
     */
    private static class MainThread implements Runnable {

        private String threadName = "main-thread";

        private Object lock;

        public MainThread(Object lock) {
            this.lock = lock;
        }

        public void run() {
            synchronized (lock) {
                for (int i = 1; i < 100; i += 2) {
                    System.out.println(threadName + "print:" + i);
                    try {
                        lock.wait();
                        TimeUnit.MILLISECONDS.sleep(10);
                        lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * sub thread print even number
     */
    private static class SubThread implements Runnable {

        private String threadName = "sub-thread";

        private Object lock;

        public SubThread(Object lock) {
            this.lock = lock;
        }

        public void run() {
            synchronized (lock) {
                for (int i = 2; i < 100; i += 2) {
                    System.out.println(threadName + " print:" + i);
                    try {
                        lock.notifyAll();
                        TimeUnit.MILLISECONDS.sleep(10);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


