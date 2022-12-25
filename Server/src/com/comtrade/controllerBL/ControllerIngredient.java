package com.comtrade.controllerBL;

import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.systemOperation.Ingredients.AddIngredient;
import com.comtrade.systemOperation.Ingredients.ReturnIngredientsService;
import com.comtrade.systemOperation.Ingredients.ReturnIngredientsServiceQuantity;
import com.comtrade.systemOperation.Ingredients.UpdateIngredientsService;
import com.comtrade.transfer.TransferClass;

public class ControllerIngredient implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
            case RETURN_INGREDIENTS:
                genericSystemOperation = new ReturnIngredientsService();
                break;
            case POST:
                genericSystemOperation = new AddIngredient();
                break;
            case RETURN_INGREDIENTS_WITH_QUANTITY_BIGGER_THAN_0:
                genericSystemOperation = new ReturnIngredientsServiceQuantity();
                break;
            case PUT:
                genericSystemOperation = new UpdateIngredientsService();
                break;
            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }
}
