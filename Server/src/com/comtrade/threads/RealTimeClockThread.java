package com.comtrade.threads;
import javax.swing.*;
public class RealTimeClockThread extends Thread {

    private final JLabel label1;

    public RealTimeClockThread(JLabel label1) {

        this.label1 = label1;
        start();
    }

    @Override
    public void run() {
        while(true){
            label1.setText("Сервер начал работу");
        }
    }
}