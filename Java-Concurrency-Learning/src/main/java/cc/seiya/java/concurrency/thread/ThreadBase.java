package cc.seiya.java.concurrency.thread;

import java.util.concurrent.*;

/**
 * @author: libo
 * @date: 2018/1/31 14:23
 */
public class ThreadBase {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        extendsThread();
//        implemntsRunnable();
        callable();
    }

    /**
     * 继承 thread 类
     */
    private static void extendsThread() {
        for (int i = 0; i < 10; i++) {
            MyThreadByThread thread = new MyThreadByThread(i);
            thread.start();
            System.out.println("thread info :" + thread.getName() + "," + thread.getId());
        }
    }

    /**
     * 实现runnable 接口
     */
    private static void implemntsRunnable() {
        for (int i = 0; i < 10; i++) {
            MyRunnable runnable = new MyRunnable(i);
            Thread thread = new Thread(runnable);
            thread.start();
            System.out.println("thread info :" + thread.getName() + "," + thread.getId());
        }
    }

    /**
     * ThreadPoolExecutor
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void callable() throws ExecutionException, InterruptedException {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = new ThreadPoolExecutor(cpuNum, cpuNum,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


        for (int i = 0; i < 10; i++) {
            String threadName = "t" + i;
            Future<String> future = pool.submit(new MyCallable(threadName));
            System.out.println(threadName + ",return value is :" + future.get());
        }

        pool.shutdown();
    }

}
