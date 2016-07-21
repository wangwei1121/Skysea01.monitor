import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.skysea.monitor.web.Keys;

public class LogClient {
	public static void main(String[] args) throws Exception {  
        for (int i = 0; i < 1; i++) {  
            final int idx = i;  
            new Thread(new MyRunnable(idx)).start();  
        }  
    }  
      
    private static final class MyRunnable implements Runnable {  
          
        private final int idx;  
  
        private MyRunnable(int idx) {  
            this.idx = idx;  
        }  
  
        public void run() {  
            SocketChannel socketChannel = null;  
            try {  
                socketChannel = SocketChannel.open();  
                InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8111);  
                socketChannel.connect(socketAddress);  
  
                sendData(socketChannel, "log is error 送到家手机电视");  
                String receiveData = receiveData(socketChannel);
                System.out.println("LogClient--" + idx + "-->" + receiveData);
            } catch (Exception e) {  
                e.printStackTrace();
            } finally {  
                try {  
                    socketChannel.close();  
                } catch(Exception ex) {}  
            }  
        }  
  
        private void sendData(SocketChannel socketChannel, String data) throws IOException {  
            ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(Keys.SYS_ENCODING));  
            socketChannel.write(buffer);  
            socketChannel.socket().shutdownOutput();  
        }  
  
        private static String receiveData(SocketChannel socketChannel) throws IOException {  
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        ByteBuffer buffer = ByteBuffer.allocate(1024);  
	        try {  
	            byte[] bytes = new byte[1024];
	            int size = 0;  
	            while ((size = socketChannel.read(buffer)) >= 0) {
	                buffer.flip();
	                buffer.get(bytes,0,size);
	                baos.write(bytes,0,size);
	                buffer.clear();
	            }
	            String resultStr = new String(baos.toByteArray(), Keys.SYS_ENCODING);
				return resultStr;
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally {
	            try {  
	                baos.close();  
	            } catch(Exception e) {
	            	e.printStackTrace();
	            }  
	        }
	        return null;
	    } 
    }
}
