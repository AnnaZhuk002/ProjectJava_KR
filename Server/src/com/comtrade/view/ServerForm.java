package com.comtrade.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.threads.RealTimeClockThread;
import com.comtrade.threads.ServerThread;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ServerForm extends JFrame {

	private JPanel contentPane1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerForm frame = new ServerForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ServerForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		JLabel lblWatch = new JLabel("Ожидание");
		lblWatch.setBounds(135, 80, 170, 14);
		contentPane1.add(lblWatch);

		JButton btnNewButton = new JButton("Начать работу на сервере");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ServerThread serverThread = new ServerThread();
				serverThread.start();
				RealTimeClockThread clockThread = new RealTimeClockThread(lblWatch);
			}
		});
		btnNewButton.setBounds(115, 120, 190, 23);
		contentPane1.add(btnNewButton);
	}
}