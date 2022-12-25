package com.comtrade.systemOperation.user;

import com.comtrade.broker.Broker;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.comtrade.systemOperation.GenericSystemOperation;
import java.sql.SQLException;

public class DeleteUserService extends GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException{
        User user = (User) transferClass.getRequest();
        Broker broker = new Broker();
        broker.deleteUser(user);
    }
}
