package com.comtrade.Bill;

import com.comtrade.transfer.TransferClass;
import com.comtrade.broker.Broker;

import java.io.IOException;
import java.sql.SQLException;

public class ReturnBillService extends com.comtrade.systemOperation.GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException {

    		int idBill = (int) transferClass.getRequest();
    		Broker broker = new Broker();
    	    transferClass.setResponse(broker.returnAllBills(idBill));

    }
}