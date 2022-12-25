package com.comtrade.controllerBL;

import com.comtrade.Bill.AddBillService;
import com.comtrade.Bill.ReturnBillService;
import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.transfer.TransferClass;

public class ControllerBill implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
            case POST:
                genericSystemOperation = new AddBillService();
                break;
            case ADDBILL:
            	genericSystemOperation = new AddBillService();
            	break;
            case RETURN_ALL_BILLS:
            	genericSystemOperation = new ReturnBillService();
            	break;
            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }
}