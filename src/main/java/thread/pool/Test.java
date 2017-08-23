package thread.pool;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class Test {
    public static void main(String[] args) {
        ThreadPooledSocketServer server = new ThreadPooledSocketServer(1234);
        new Thread(server).start();

        try {
            // waiting a minute
            Thread.sleep(60*1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
