package com.comtrade.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class WaiterForm extends JFrame {

	private JPanel contentPane;
	int table_number_traveling = 0;
	List<Integer> tableNumberList = new ArrayList<>();
	RestaurantTable restaurantTableGET = new RestaurantTable();

	public WaiterForm(User user) throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 100, 719, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestaurantTables = new JLabel("Выберите столик");
		lblRestaurantTables.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRestaurantTables.setBounds(219, 95, 281, 14);
		contentPane.add(lblRestaurantTables);
		
		JComboBox cbTables = new JComboBox();
		cbTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cbTables.setBounds(219, 181, 183, 22);
		contentPane.add(cbTables);
		
		fillInComboBox(cbTables);
		
		JButton btnAccess = new JButton("Попасть на выбранный столик");
		btnAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer a =  (Integer) cbTables.getSelectedItem();
				RestaurantTable restaurantTable = new RestaurantTable();
				restaurantTable.setTable_number(a);
				restaurantTableGET.setTable_number(a);
				table_number_traveling = a;
				tableNumberList.add(a);
						new OrderForm(a,user).setVisible(true);
			}
		});

		btnAccess.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAccess.setBounds(219, 376, 200, 23);
		contentPane.add(btnAccess);
		setResizable(false);
	}

	RestaurantTable getRestaurantTableGET() {
		return restaurantTableGET;
	}

	private void fillInComboBox(JComboBox cbTables) throws InterruptedException {
		try {
				TransferClass request = TransferClass.create(null, ConstUI.RESTAURANT_TABLE,ConstBL.GET);
				List<Object> tablezz = (List<Object>) ControllerUI.getControllerUI().execute(request).getResponse();
				cbTables.removeAllItems();
				for (Object table : tablezz) {
					cbTables.addItem(table);
				}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}
}
