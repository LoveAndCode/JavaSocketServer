package thread.multi;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class Test {
    public static void main(String args[]){
        MultiThreadSocketServer server = new MultiThreadSocketServer(9000);
        new Thread(server).start();

        try {
            Thread.sleep(60*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
