package com.comtrade.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.comtrade.controllerBL.CommandBase;
import com.comtrade.controllerBL.ControllerBill;
import com.comtrade.controllerBL.ControllerIngredient;
import com.comtrade.controllerBL.ControllerRestaurantTable;
import com.comtrade.controllerBL.ControllerThread;
import com.comtrade.controllerBL.ControllerUser;
import com.comtrade.transfer.TransferClass;

public class ClientThread extends Thread {

	  private Socket clientSocket;

	    private List<ClientThread> listClients = new ArrayList<ClientThread>();

	    public void setClientSocket(Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    public Socket getClientSocket() {
	        return clientSocket;
	    }

	    public ClientThread(Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    @Override
	    public  void run() {
	        while(true) {
	            try {
					ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
	                TransferClass transferClass = (TransferClass) objectInputStream.readObject();
	                clientHandle(transferClass);
				} catch (SocketException e) {
					e.printStackTrace();
	            } catch (IOException e) {
					e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                try {
	                    clientSocket.close();
	                } catch (IOException ex) {
	                    try {
	                        clientSocket.close();
	                    } catch (IOException exc) {
	                        exc.printStackTrace();
	                    }
	                }
	            }
	        }
	    }

	    public  void send(TransferClass transferClass) {
	        // TODO Auto-generated method stub
	        try {
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
	            objectOutputStream.writeObject(transferClass);

	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    private  void clientHandle(TransferClass transferClass) throws IOException {
	        // TODO Auto-generated method stub

	        CommandBase commandBase = null;
	        switch (transferClass.getConstUI()) {
	            case USER:
	                commandBase =new ControllerUser();
	                break;
	            case INGREDIENTS:
	                commandBase =new ControllerIngredient();
	                break;
	            case RESTAURANT_TABLE:
	                commandBase =new ControllerRestaurantTable();
	                break;
	            case BILL:
	                commandBase =new ControllerBill();
	                break;
	        }
	        commandBase.execute(transferClass);
	        ControllerThread.getInstance().informOnlySendingClient(transferClass, this);

	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ClientThread that = (ClientThread) o;
	        return Objects.equals(clientSocket, that.clientSocket);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(clientSocket);
	    }
}