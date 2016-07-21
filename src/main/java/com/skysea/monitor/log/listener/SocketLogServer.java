package com.skysea.monitor.log.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.net.ServerSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SocketLogServer extends Thread {
	
	private final Logger LOG = LoggerFactory.getLogger(SocketLogServer.class);

    private int port;
    private boolean closed = false;
    private ServerSocket serverSocket;
    private List<SocketNode> socketNodeList = new ArrayList<SocketNode>();

    public SocketLogServer(int port) {
        this.port = port;
    }

    public void run() {
        final String oldThreadName = Thread.currentThread().getName();
        try {

            final String newThreadName = getServerThreadName();
            Thread.currentThread().setName(newThreadName);

            LOG.info("Listening on port " + port);
            serverSocket = getServerSocketFactory().createServerSocket(port);
            while (!closed) {
            	LOG.info("Waiting to accept a new client.");
                Socket socket = serverSocket.accept();
                LOG.info("Connected to client at " + socket);
                LOG.info("Starting new socket node.");
                SocketNode newSocketNode = new SocketNode(this, socket);
                synchronized (socketNodeList) {
                    socketNodeList.add(newSocketNode);
                }
                final String clientThreadName = getClientThreadName(socket);
                new Thread(newSocketNode, clientThreadName).start();
            }
        } catch (Exception e) {
            if (closed) {
                System.out.println("Exception in run method for a closed server. This is normal.");
            } else {
            	System.out.println("Unexpected failure in run method");
            }
        }
        finally {
            Thread.currentThread().setName(oldThreadName);
        }
    }

    /**
     * Returns the name given to the server thread.
     */
    public String getServerThreadName() {
        return String.format("Logback %s (port %d)", getClass().getSimpleName(), port);
    }

    public String getClientThreadName(Socket socket) {
        return String.format("Logback SocketNode (client: %s)", socket.getRemoteSocketAddress());
    }

    public ServerSocketFactory getServerSocketFactory() {
        return ServerSocketFactory.getDefault();
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
        if (null != serverSocket) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            	LOG.error("Failed to close serverSocket");
            	LOG.error(e.getMessage(),e);
            } finally {
                serverSocket = null;
            }
        }

        LOG.info("closing this server");
        synchronized (socketNodeList) {
            for (SocketNode sn : socketNodeList) {
                sn.close();
            }
        }
        if (socketNodeList.size() != 0) {
        	LOG.info("Was expecting a 0-sized socketNodeList after server shutdown");
        }

    }

    public void socketNodeClosing(SocketNode sn) {
    	LOG.info("Removing " + sn);
        synchronized (socketNodeList) {
            socketNodeList.remove(sn);
        }
    }
    
    public static void main(String argv[]) throws Exception {
        new SocketLogServer(8899).start();
    }

}
