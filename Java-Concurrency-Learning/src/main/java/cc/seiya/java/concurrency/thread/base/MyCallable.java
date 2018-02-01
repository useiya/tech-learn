package cc.seiya.java.concurrency.thread.base;


import java.util.concurrent.Callable;

/**
 * @author libo
 * @date 2018/1/31  22:56
 */
public class MyCallable implements Callable<String> {

    private String threadName;

    public MyCallable(String threadName) {
        this.threadName = threadName;
    }

    public String call() throws Exception {
        return System.currentTimeMillis() + "";
    }
}
