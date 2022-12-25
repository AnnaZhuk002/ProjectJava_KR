package com.comtrade.systemOperation.Ingredients;

import com.comtrade.transfer.TransferClass;
import com.comtrade.broker.Broker;
import com.comtrade.domain.Ingredients;

import java.io.IOException;
import java.sql.SQLException;

public class AddIngredient extends com.comtrade.systemOperation.GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException {

    	Ingredients ingredient = (Ingredients) transferClass.getRequest();
        Broker broker = new Broker();
        broker.addIngredient(ingredient);

    }
}