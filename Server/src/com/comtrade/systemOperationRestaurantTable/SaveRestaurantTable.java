package com.comtrade.systemOperationRestaurantTable;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.transfer.TransferClass;

public class SaveRestaurantTable extends GenericSystemOperation {
	
    public void executeSpecificOperation(TransferClass transferClass) throws SQLException {
    	
        RestaurantTable restaurantTable = (RestaurantTable) transferClass.getRequest();
        Broker broker = new Broker();
        broker.saveRestaurantTable(restaurantTable);

    }
}
