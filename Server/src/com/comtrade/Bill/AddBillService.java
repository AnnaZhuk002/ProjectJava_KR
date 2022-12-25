package com.comtrade.Bill;

import com.comtrade.broker.Broker;
import com.comtrade.domain.Bill;
import com.comtrade.transfer.TransferClass;

import com.comtrade.systemOperation.GenericSystemOperation;

import java.sql.SQLException;

public class AddBillService extends GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException {
        Bill bill = (Bill) transferClass.getRequest();
        Broker broker = new Broker();
        broker.saveBill(bill);
    }
}