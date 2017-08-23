package thread.pool.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class ThreadPool {

    private ThreadPoolQueue queue;
    private List<ThreadPoolRunnable> runnableList = new ArrayList<ThreadPoolRunnable>();
    private volatile boolean running = true;


    // Initialize Thread pool
    public ThreadPool(int MAX_THREAD_NUM, int MAX_QUEUE_SIZE) {
        queue = new ThreadPoolQueue(MAX_QUEUE_SIZE);
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            runnableList.add(new ThreadPoolRunnable(i, queue));
        }
        for (ThreadPoolRunnable r : runnableList) {
            new Thread(r).start();
        }
    }

    // Execute thread pool
    public synchronized void execute(Runnable item) throws Exception {
        if (!running) {
            throw new Exception("Thread Pool is not running");
        }
        queue.enqueue(item);
    }

    // Thread stop
    public synchronized void stop() throws InterruptedException {
        running = false;
        for (ThreadPoolRunnable r : runnableList) {
            r.stop();
        }
    }

    // Change debug flag of runnable console
    public void toogleDebugWithRunnable(boolean flag){
        for(ThreadPoolRunnable r : runnableList){
            r.toggleDebug(flag);
        }
    }


    // Change debug flag of queue console
    public void toggleDebugWithQueue(boolean flag){
        queue.toggleDebug(flag);
    }
}
