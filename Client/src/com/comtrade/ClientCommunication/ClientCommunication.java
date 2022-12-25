package com.comtrade.ClientCommunication;

import com.comtrade.ClientThread.ReadThread;
import com.comtrade.constants.Const_IP_Port;
import com.comtrade.transfer.TransferClass;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;
public class ClientCommunication {
    private static ClientCommunication instance;
    private Socket socket;
    private int userID;
    public void setUserID(int userID) {
        this.userID = userID;
    }

    private ClientCommunication() {
        try {
            socket = new Socket(Const_IP_Port.IP_ADRESS.getIPAdress(), Const_IP_Port.PORT.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientCommunication getInstance() {
        if (instance == null) {
            instance = new ClientCommunication();
        }
        return instance;
    }

    public void send(TransferClass transferClass) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(transferClass);
        objectOutputStream.flush();
    }

    public TransferClass read() throws IOException, ClassNotFoundException {
        TransferClass transferClass = new TransferClass();

        try {
            ExecutorService service = Executors.newSingleThreadExecutor();
            ReadThread threadRead = new ReadThread(socket, userID);
            transferClass = service.submit(threadRead).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return transferClass;
    }
}