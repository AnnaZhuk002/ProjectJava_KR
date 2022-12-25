package com.comtrade.controllerBL;

import com.comtrade.transfer.TransferClass;

public interface CommandBase {

    void execute(TransferClass transferClass);
}