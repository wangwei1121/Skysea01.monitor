import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skysea.log.LoggingEventVO;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;

public class Log4jMain {
	private static final Logger logger = LoggerFactory.getLogger(Log4jMain.class);

	public static void main(String[] args) throws Exception {
		for(int i=0;i<10;i++){
			try {
				throw new Exception("errorssasdsd sdsds");
			} catch (Exception e) {
				logger.error("error Log  ",e);	
			}
			
		}
		logger.error("error Log.");
		Log4jMain log4jMain = new Log4jMain();
	     synchronized (log4jMain) {
	    	  log4jMain.wait();
	     }
		
//		Socket socket = new Socket("127.0.0.1", 8899);
//		for(int i=0;i<2;i++){
//			System.out.println(i);
//			System.out.println(socket.isConnected());
//			System.out.println(socket.isClosed());
//			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//			LoggingEvent event  = new LoggingEvent();
//			event.setMessage("@@@@@@@EDDDDDDDDDDD@@@@@@@-->" + i);
//			event.setLevel(Level.INFO);
//			oos.writeObject(LoggingEventVO.build(event));
//			oos.close();  
//		}
//        socket.close();
	}
}
