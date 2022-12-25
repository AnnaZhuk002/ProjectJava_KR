package com.comtrade.systemOperation.Ingredients;

import com.comtrade.transfer.TransferClass;
import com.comtrade.broker.Broker;

import java.io.IOException;
import java.sql.SQLException;

public class ReturnIngredientsServiceQuantity extends com.comtrade.systemOperation.GenericSystemOperation {
    @Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException {

    		int idIngredient = (int) transferClass.getRequest();
    		Broker broker = new Broker();
    	    transferClass.setResponse(broker.getIngredientsWithQuantityBiggerThanZero(idIngredient));

    }
}