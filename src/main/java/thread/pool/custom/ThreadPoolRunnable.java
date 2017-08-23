package thread.pool.custom;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class ThreadPoolRunnable implements Runnable {

    private int id; // Runnable id
    private ThreadPoolQueue queue;
    private volatile boolean running = true;
    private boolean DEBUG = false;

    // Initialize
    public ThreadPoolRunnable(int THREAD_ID, ThreadPoolQueue queue){
        this.id = THREAD_ID;
        this.queue = queue;
        console("ThreadPoolRunnable["+id+"] is created.");
    }

    public void run() {
        while(running){
            try {
                Thread.sleep(10);
                console("ThreadPoolRunnable["+id+"] is working.");
                Runnable r = (Runnable) queue.dequeue();
                r.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stop();
            }
        }
    }

    public void stop(){
        running = false;
        console("ThreadPoolRunnable["+id+"] is stopped.");
    }

    public void toggleDebug(boolean flag){
        this.DEBUG = flag;
    }

    public void console(final String msg){
        if(DEBUG)
            System.out.println(msg);
    }
}
