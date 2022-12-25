package com.comtrade.controllerBL;

import com.comtrade.transfer.TransferClass;
import com.comtrade.threads.ClientThread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ControllerThread {

    private static ControllerThread instance;
    private List<ClientThread> listClients = new ArrayList<>();
    private Socket socket;

    private ControllerThread(){

    }

    public static ControllerThread getInstance() {
        if (instance ==null){
            synchronized (ControllerThread.class) {
                if(instance == null) {
                    instance = new ControllerThread();
                }
            }
        }
        return instance;
    }

    public synchronized List<ClientThread> getListClients() {
        return listClients;
    }

    public synchronized void addClientThread(ClientThread clientThread){
        listClients.add(clientThread);
    }

    public  void informAllClients(TransferClass transferClass, ClientThread clientThread) throws IOException {
        for (ClientThread client: listClients) {
            if (!clientThread.equals(client)){
                clientThread.send(transferClass);

            }
        }
    }

    public  void informOnlySendingClient(TransferClass transferClass, ClientThread clientThread) throws IOException {
        for (ClientThread client: listClients) {
            if (client.equals(clientThread)){
                client.send(transferClass);

            }
        }
    }

    public void removeFromList(ClientThread clientThread){
        for (ClientThread nit:listClients             ) {
            if (nit!=clientThread){
                listClients.remove(clientThread);
                break;
            }
        }
    }
}