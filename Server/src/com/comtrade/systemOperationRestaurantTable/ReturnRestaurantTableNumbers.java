package com.comtrade.systemOperationRestaurantTable;

import java.io.IOException;
import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.transfer.TransferClass;

public class ReturnRestaurantTableNumbers extends GenericSystemOperation {

	@Override
	public void executeSpecificOperation(TransferClass transferClass) throws SQLException, InterruptedException, IOException 
	{
		RestaurantTable restaurantTable = (RestaurantTable) transferClass.getRequest();
		Broker broker = new Broker();
		transferClass.setResponse(broker.returnAllTableNumbers(restaurantTable));
		
	}
}
