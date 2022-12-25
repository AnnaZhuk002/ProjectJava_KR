package com.comtrade.systemOperation.user;

import com.comtrade.transfer.TransferClass;
import com.comtrade.broker.Broker;

import java.io.IOException;
import java.sql.SQLException;

public class ReturnUserService extends com.comtrade.systemOperation.GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException {

    		int idOwner = (int) transferClass.getRequest();
    		Broker broker = new Broker();
    	    transferClass.setResponse(broker.returnUsers(idOwner));

    }
}