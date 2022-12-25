package com.comtrade.view;

import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.Ingredients;
import com.comtrade.transfer.TransferClass;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddIngredients extends JFrame {
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfMeasurement;
	private JTable table;
	private JTextField tfQuantity;
	private List <Ingredients> ingredientList = null;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JButton btnUpdate;
	private JTextField tfIng;
	
	public AddIngredients(Ingredients ingredients) throws ClassNotFoundException, IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 714, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Название");
		lblNewLabel.setBounds(10, 11, 70, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Цена");
		lblPrice.setBounds(10, 36, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblMeasurement = new JLabel("Измерение");
		lblMeasurement.setBounds(10, 63, 134, 14);
		contentPane.add(lblMeasurement);
		
		JButton btnAdd = new JButton("Добавить");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String measurement = tfMeasurement.getText();
				double quantity = Double.parseDouble(tfQuantity.getText());
				double price = Double.parseDouble(tfPrice.getText());
				Ingredients ingredient = new Ingredients(name,measurement,quantity,price);
				try {
					TransferClass transferClass = ControllerUI.getControllerUI().execute(
							TransferClass.create(ingredient, ConstUI.INGREDIENTS,ConstBL.POST));
					JOptionPane.showMessageDialog(null, "Товар добавлен успешно");
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Товар не был добавлен");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						showIngredients(ingredients);
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
			}
		});
		btnAdd.setBounds(39, 112, 95, 23);
		contentPane.add(btnAdd);
		
		tfName = new JTextField();
		tfName.setBounds(88, 8, 86, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(88, 33, 86, 20);
		contentPane.add(tfPrice);
		
		tfMeasurement = new JTextField();
		tfMeasurement.setColumns(10);
		tfMeasurement.setBounds(88, 56, 86, 20);
		contentPane.add(tfMeasurement);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(42, 167, 621, 376);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				tfName.setText(table.getModel().getValueAt(row,1).toString());
				tfPrice.setText(table.getModel().getValueAt(row, 2).toString());
				tfMeasurement.setText(table.getModel().getValueAt(row,3 ).toString());
				tfQuantity.setText(table.getModel().getValueAt(row, 4).toString());
				tfIng.setText(table.getModel().getValueAt(row, 0).toString());
						}
			}
		);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(88, 81, 86, 20);
		contentPane.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Количество");
		lblQuantity.setBounds(10, 87, 75, 14);
		contentPane.add(lblQuantity);

		btnUpdate = new JButton("Изменить информацию о товаре");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double quantity = Double.parseDouble(tfQuantity.getText());
				int idIngredient = Integer.parseInt(tfIng.getText());
				Ingredients ingredient = new Ingredients();
				ingredient.setId_ingredient(idIngredient);
				ingredient.setQuantity(quantity);
				try {
					TransferClass transferClass = ControllerUI.getControllerUI().execute(
							TransferClass.create(ingredient, ConstUI.INGREDIENTS, ConstBL.PUT));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						showIngredients(ingredients);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setBounds(270, 112, 250, 23);
		contentPane.add(btnUpdate);
		
		tfIng = new JTextField();
		tfIng.setBounds(589, 113, 86, 20);
		contentPane.add(tfIng);
		tfIng.setColumns(10);

		JLabel lblIng = new JLabel("ID Товара");
		lblIng.setBounds(530, 113, 90, 14);
		contentPane.add(lblIng);

		Object [] columns = {"ID Товара","Наименование товара","Цена","Измерение","Количество"};
		dtm.addColumn(columns[0]);
		dtm.addColumn(columns[1]);
		dtm.addColumn(columns[2]);
		dtm.addColumn(columns[3]);
		dtm.addColumn(columns[4]);
		showIngredients(ingredients);
	}
	private void showIngredients(Ingredients ingredients) throws ClassNotFoundException, IOException, InterruptedException {
		TransferClass transferClass = TransferClass.create(1, ConstUI.INGREDIENTS, ConstBL.RETURN_INGREDIENTS);
		ingredientList = (List<Ingredients>) ControllerUI.getControllerUI().execute(transferClass).getResponse();
		dtm.setRowCount(0);
		for (Ingredients ingredients1 : ingredientList) {
			Object[] columns = { ingredients1.getId_ingredient(), ingredients1.getIngredient_name(), ingredients1.getprice(), ingredients1.getQuantity_measure(),
					ingredients1.getQuantity() };
			dtm.addRow(columns);
		}
	}
}
