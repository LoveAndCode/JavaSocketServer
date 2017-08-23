package signle;

/**
 * Created by Jang Jeong Hyeon on 2017-08-24.
 */
public class Test {

    public static void main(String args[]){
        SingleThreadSocketServer server = new SingleThreadSocketServer(9000);
        new Thread(server).start();

        try{
            Thread.sleep(10*1000);
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
