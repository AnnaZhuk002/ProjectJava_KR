package com.comtrade.transfer;

import java.io.IOException;
import java.io.Serializable;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;

public class TransferClass implements Serializable {

    private Object request;
    private String message;
    private ConstUI constUI;
    private ConstBL constBL;
    private Object response;
    
    public static TransferClass create(Object request, ConstUI constUI, ConstBL constBL) throws ClassNotFoundException, IOException, InterruptedException {
        TransferClass transferClass = new TransferClass();
        transferClass.setRequest(request);
        transferClass.setConstUI(constUI);
        transferClass.setConstBL(constBL);
        return transferClass;
    }
    
	public Object getRequest() {
		return request;
	}
	public void setRequest(Object request) {
		this.request = request;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ConstUI getConstUI() {
		return constUI;
	}
	public void setConstUI(ConstUI constUI) {
		this.constUI = constUI;
	}
	public ConstBL getConstBL() {
		return constBL;
	}
	public void setConstBL(ConstBL constBL) {
		this.constBL = constBL;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}