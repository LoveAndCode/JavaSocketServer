package thread.runnable;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class WorkerRunnable implements Runnable{
    protected Socket client = null;
    protected String serverText = null;

    public WorkerRunnable(Socket client, String serverText) {
        this.client = client;
        this.serverText = serverText;
    }

    public void run() {
        try{
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                    this.serverText + " - " +
                    new Timestamp(time).toString() +
                    "").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed"+ new Timestamp(time).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
