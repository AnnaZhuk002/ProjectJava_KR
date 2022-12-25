package com.comtrade.systemOperation.Ingredients;

import java.io.IOException;
import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.Ingredients;
import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.transfer.TransferClass;

public class UpdateIngredientsService extends GenericSystemOperation {

	@Override
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException {

		Ingredients ingredient = (Ingredients) transferClass.getRequest();
		Broker broker = new Broker();
	    transferClass.setResponse(broker.updateIngredients(ingredient));

}
}
