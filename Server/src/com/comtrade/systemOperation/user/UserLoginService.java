package com.comtrade.systemOperation.user;

import com.comtrade.domain.User;
import com.comtrade.broker.Broker;
import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.transfer.TransferClass;

public class UserLoginService extends GenericSystemOperation {

    @Override
    public void executeSpecificOperation(TransferClass transferClass) {
        User user = (User) transferClass.getRequest();
        Broker broker = new Broker();
        transferClass.setResponse(broker.returnRoleUser(user));
    }
}