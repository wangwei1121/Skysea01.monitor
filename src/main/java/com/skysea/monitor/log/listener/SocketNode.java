package com.skysea.monitor.log.listener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;

public class SocketNode implements Runnable {
	
	private final Logger LOG = LoggerFactory.getLogger(SocketLogServer.class);
	
	private Socket socket;
	
	private ObjectInputStream ois;
	
	private SocketAddress remoteSocketAddress;

	private boolean closed = false;
	private SocketLogServer socketServer;

	public SocketNode(SocketLogServer socketServer, Socket socket) {
		this.socketServer = socketServer;
		this.socket = socket;
		remoteSocketAddress = socket.getRemoteSocketAddress();
	}

	public void run() {
		try {
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (Exception e) {
			closed = true;
			LOG.error(e.getMessage(),e);
		}
		try {
			while (!closed) {
				ILoggingEvent event = (ILoggingEvent) ois.readObject();
				LOG.info(DateFormatUtils.format(event.getTimeStamp(), "yyyy-MM-dd HH:mm:ss,SSS") + " " +  event.getLevel() + " " + event.getLoggerName() + " " + event.getFormattedMessage());
				StackTraceElement[] trace = event.getCallerData();
				for (StackTraceElement traceElement : trace){
					LOG.info("\tat " + traceElement);					
				}
				StackTraceElementProxy[] array = event.getThrowableProxy().getStackTraceElementProxyArray();
				for(StackTraceElementProxy stp:array){
					LOG.info("\tat " + stp.getStackTraceElement());	
				}
			}
		} catch (java.io.EOFException e) {
			LOG.error(e.getMessage(),e);
		} catch (java.net.SocketException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		socketServer.socketNodeClosing(this);
		close();
	}

	public void close() {
		if (closed) {
			return;
		}
		closed = true;
		if (null != ois) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				ois = null;
			}
		}
		if(null != socket){
			try {
				socket.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			} finally{
				socket = null;
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + remoteSocketAddress.toString();
	}
}
