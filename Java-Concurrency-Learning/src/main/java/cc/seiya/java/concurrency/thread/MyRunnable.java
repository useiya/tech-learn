package cc.seiya.java.concurrency.thread;

/**
 * @author: libo
 * @date: 2018/1/31 14:27
 */
public class MyRunnable implements Runnable {

    private int idx;

    public MyRunnable(int idx) {
        this.idx = idx;
    }

    public void run() {
        System.out.println(getClass().getName() + "[" + idx + "] implements Runnable ");
    }
}
