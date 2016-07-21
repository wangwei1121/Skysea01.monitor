import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.skysea.log.LoggingEventVO;
import com.skysea.monitor.web.Keys;

public class LogServer {
	public static void main(String args[]) throws IOException {
		int port = 8899;
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println(socket);
			System.out.println("新增连接：" + socket.getInetAddress() + ":" + socket.getPort());
			new Thread(new Task(socket)).start();
		}
	}

	/**
	 * 用来处理Socket请求的
	 */
	static class Task implements Runnable {
		private Socket socket;
		public Task(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
            	Object event =  ois.readObject();
 	            System.out.println(event);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println("关闭连接:" + socket.getInetAddress() + ":" + socket.getPort());
					if (socket != null){
						socket.close();		
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
