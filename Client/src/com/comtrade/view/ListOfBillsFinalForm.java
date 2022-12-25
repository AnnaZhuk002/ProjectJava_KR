package com.comtrade.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.Bill;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

public class ListOfBillsFinalForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel dtm1 = new DefaultTableModel();
	private List <Bill> listOfBills = null;

	public ListOfBillsFinalForm(Bill bill) throws ClassNotFoundException, IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 714, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Статус заказа");
		lblNewLabel.setBounds(27, 443, 88, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 678, 371);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm1);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("Акции");
		lblNewLabel_3.setBounds(27, 393, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Никакие акции не применяются");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(193, 392, 271, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Тип оплаты");
		lblNewLabel_5.setBounds(27, 418, 102, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Наличные");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(193, 417, 65, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblTime = new JLabel("");
		lblTime.setBounds(193, 330, 46, 14);
		contentPane.add(lblTime);
		
		JLabel lblPrice = new JLabel("");
		lblPrice.setBounds(193, 367, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblNewLabel_2 = new JLabel("Оплачено");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(193, 442, 65, 14);
		contentPane.add(lblNewLabel_2);

		Object[] columns = {"Номер столика", "Время заказа", "ID_Ресторана", "Имя официанта", "Окончательная цена"};
		dtm1.addColumn(columns[0]);
		dtm1.addColumn(columns[1]);
		dtm1.addColumn(columns[2]);
		dtm1.addColumn(columns[3]);
		dtm1.addColumn(columns[4]);
		showAllBillsInTable(bill);
		
	}
	private void showAllBillsInTable(Bill bill) throws ClassNotFoundException, IOException, InterruptedException {
		TransferClass transferClass = TransferClass.create(1, ConstUI.BILL, ConstBL.RETURN_ALL_BILLS);
		listOfBills = (List<Bill>) ControllerUI.getControllerUI().execute(transferClass).getResponse();
		dtm1.setRowCount(0);
		for (Bill bill1 : listOfBills) {
			Object[] columns = {bill1.getid_restaurantTable(),bill1.getDateTime() ,bill1.getIdRestaurant(), bill1.getWaiterName(),
					bill1.getFinalAmount()};
			dtm1.addRow(columns);
		}
	}
}
