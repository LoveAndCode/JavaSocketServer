package thread.pool.custom;

import java.util.LinkedList;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class ThreadPoolQueue {
    // Create queue for saving items
    private LinkedList<Object> queue = new LinkedList<Object>();
    // Default maximum queue size
    private int MAX_QUEUE_SIZE = 5;
    // Debug flag
    private boolean DEBUG = false;


    public ThreadPoolQueue(int MAX_QUEUE_SIZE){
        this.MAX_QUEUE_SIZE = MAX_QUEUE_SIZE;
    }

    // Insert item
    public synchronized void enqueue(Object item) throws InterruptedException{
        // if queue size is equal to MAX_QUEUE_SIZE
        while( queue.size() == MAX_QUEUE_SIZE){
            console("enqueue waiting....");
            wait();
        }

        if(queue.size() == 0){
            console("enqueue notifyall...");
            notifyAll();
        }

        console("enqueue adding...");
        queue.add(item);
    }

    public synchronized Object dequeue() throws InterruptedException{
        while(queue.size() == 0){
            console("dequeue waiting");
            wait();
        }

        if(queue.size() == MAX_QUEUE_SIZE){
            console("dequeue notifyall...");
            notifyAll();
        }

        console("dequeue removing...");
        return queue.remove(0);
    }

    public void toggleDebug(boolean flag){
        this.DEBUG = flag;
    }

    public void console(final String msg){
        if(DEBUG)
            System.out.println(msg);
    }
}
