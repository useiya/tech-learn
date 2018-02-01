package cc.seiya.java.concurrency.thread.base;

/**
 * 继承方式实现多线程
 *
 * @author: libo
 * @date: 2018/1/31 14:21
 */
public class MyThreadByThread extends Thread {

    private int idx;

    public MyThreadByThread(int idx) {
        this.idx = idx;
    }

    @Override
    public void run() {

        System.out.println("MyThread [" + idx + "] by extends ... ");

    }
}
