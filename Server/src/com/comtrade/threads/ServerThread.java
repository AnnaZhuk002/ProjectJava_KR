package com.comtrade.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.comtrade.constants.Const_IP_Port;
import com.comtrade.controllerBL.ControllerThread;

public class ServerThread extends Thread {

	  @Override
	    public void run() {
	        startServer();
	    }

	    private void startServer() {
	        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	        try(ServerSocket serverSocket = new ServerSocket(Const_IP_Port.PORT.getPort())) {
	            while (true) {
	                Socket socket = serverSocket.accept();
	                ClientThread clientThread = new ClientThread(socket);
	                ControllerThread.getInstance().addClientThread(clientThread);
	                executorService.execute(clientThread);
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}