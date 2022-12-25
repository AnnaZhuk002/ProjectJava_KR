package com.comtrade.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.User;

import com.comtrade.transfer.TransferClass;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class RestaurantTablesForm extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfTableNumber;
	private JButton btnCreateTables;

	public RestaurantTablesForm(User user) throws Exception {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Создать стол в ресторане");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(150, 80, 250, 14);
		contentPane.add(lblNewLabel);

		tfTableNumber = new JTextField();
		tfTableNumber.setBounds(250, 160, 86, 20);
		contentPane.add(tfTableNumber);
		tfTableNumber.setColumns(10);

		JLabel lblTableNumber = new JLabel("Номер стола");
		lblTableNumber.setBounds(160, 160, 103, 14);
		contentPane.add(lblTableNumber);

		btnCreateTables = new JButton("Создать номер стола");
		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tableNumber = Integer.parseInt(tfTableNumber.getText());
				RestaurantTable restaurantTable = new RestaurantTable();
				restaurantTable.setTable_number(tableNumber);

				try {
					TransferClass transferClass = ControllerUI.getControllerUI()
							.execute(TransferClass.create(restaurantTable, ConstUI.RESTAURANT_TABLE, ConstBL.POST));
					JOptionPane.showMessageDialog(null,"Стол с номером "+ tfTableNumber.getText()+" был создан");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreateTables.setBounds(170, 190, 161, 23);
		contentPane.add(btnCreateTables);
	}
}
