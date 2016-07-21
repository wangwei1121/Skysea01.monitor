package com.skysea.log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketAddress;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class SocketNode implements Runnable{

    Socket socket;
    ObjectInputStream ois;
    SocketAddress remoteSocketAddress;

    boolean closed = false;
    SimpleSocketServer socketServer;

    public SocketNode(SimpleSocketServer socketServer, Socket socket) {
        this.socketServer = socketServer;
        this.socket = socket;
        remoteSocketAddress = socket.getRemoteSocketAddress();
    }

    // public
    // void finalize() {
    // System.err.println("-------------------------Finalize called");
    // System.err.flush();
    // }

    public void run() {

    	 try {
             ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
         } catch (Exception e) {
        	 e.printStackTrace();
             closed = true;
         }

         ILoggingEvent event;
         try {
             while (!closed) {
            	 System.out.println(1);
                 // read an event from the wire
                 event = (ILoggingEvent) ois.readObject();
                 // get a logger from the hierarchy. The name of the logger is taken to
                 // be the name contained in the event.
                 // apply the logger-level filter
                 System.out.println("#############" + event.getMessage());
             }
         } catch (java.io.EOFException e) {
             e.printStackTrace();
         } catch (java.net.SocketException e) {
        	 e.printStackTrace();
         } catch (IOException e) {
        	 e.printStackTrace();
         } catch (Exception e) {
        	 e.printStackTrace();
         }

         socketServer.socketNodeClosing(this);
         close();
    }

    void close() {
        if (closed) {
            return;
        }
        closed = true;
        if (ois != null) {
            try {
                ois.close();
            } catch (IOException e) {
            	e.printStackTrace();
            } finally {
                ois = null;
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + remoteSocketAddress.toString();
    }
}
