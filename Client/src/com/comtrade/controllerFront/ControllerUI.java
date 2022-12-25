package com.comtrade.controllerFront;

import com.comtrade.transfer.TransferClass;
import com.comtrade.ClientCommunication.ClientCommunication;
import java.io.IOException;

public class ControllerUI {

    private static ControllerUI instance;

    private ControllerUI(){
    }

    public static ControllerUI getControllerUI() {
        if (instance==null){
            instance = new ControllerUI();
        }
        return instance;
    }

    public TransferClass execute(TransferClass transferClass) throws IOException, ClassNotFoundException, InterruptedException {
        ClientCommunication.getInstance().send(transferClass);
        return ClientCommunication.getInstance().read();

    }
}