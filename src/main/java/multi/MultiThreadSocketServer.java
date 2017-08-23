package multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class MultiThreadSocketServer implements Runnable {
    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runnIngThread = null;

    public MultiThreadSocketServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() {
        synchronized (this) {
            this.runnIngThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped) {
            Socket client = null;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server is stopped..");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(
                    new WorkerRunnable(client, "MultiThreadSocketServer")
            ).start();
        }
        System.out.println("Server is stopped....");
    }

    public synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Can't open port " + this.serverPort, e);
        }
    }
}
