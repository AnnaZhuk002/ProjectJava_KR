package com.comtrade.controllerBL;

import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.systemOperation.user.ReturnUserService;
import com.comtrade.systemOperation.user.UserLoginService;
import com.comtrade.systemOperation.user.UserSaveService;
import com.comtrade.transfer.TransferClass;
import com.comtrade.systemOperation.user.DeleteUserService;
import com.comtrade.systemOperation.user.EditUserService;
public class ControllerUser implements CommandBase{

    @Override
    public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
            case POST:
                genericSystemOperation = new UserSaveService();
                break;
            case GET_LOGIN:
                genericSystemOperation = new UserLoginService();
                break;
            case RETURN_USERS:
                genericSystemOperation = new ReturnUserService();
                break;
            case DELETE_USER:
                genericSystemOperation = new DeleteUserService();
                break;
            case EDIT_USER:
                genericSystemOperation = new EditUserService();
                break;
            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }
}