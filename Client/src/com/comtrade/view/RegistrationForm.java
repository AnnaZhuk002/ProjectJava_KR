package com.comtrade.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegistrationForm extends JFrame {

	private JPanel lblConfirmPassword;
	private JTextField tfFirstName;
	private JTextField tfUsername;
	private JTextField tfRole;
	private JPasswordField passwordField;

	public RegistrationForm() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 713, 592);
		lblConfirmPassword = new JPanel();
		lblConfirmPassword.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(lblConfirmPassword);
		lblConfirmPassword.setLayout(null);
		
		JLabel lblFirstName = new JLabel("Имя");
		lblFirstName.setBounds(98, 85, 69, 14);
		lblConfirmPassword.add(lblFirstName);
		
		JLabel lblUsername = new JLabel("Имя пользователя");
		lblUsername.setBounds(98, 134, 120, 14);
		lblConfirmPassword.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Пароль");
		lblPassword.setBounds(98, 191, 69, 14);
		lblConfirmPassword.add(lblPassword);
		
		JLabel Role = new JLabel("Роль");
		Role.setBounds(98, 240, 69, 14);
		lblConfirmPassword.add(Role);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(213, 82, 90, 20);
		lblConfirmPassword.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(213, 131, 90, 20);
		lblConfirmPassword.add(tfUsername);
		
		tfRole = new JTextField();
		tfRole.setColumns(10);
		tfRole.setBounds(213, 237, 90, 20);
		tfRole.setEditable(false);
		tfRole.setText("waiter");
		lblConfirmPassword.add(tfRole);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(213, 188, 90, 20);
		lblConfirmPassword.add(passwordField);
		
		JButton btnRegistration = new JButton("Зарегистрируйтесь сейчас!");
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.copyValueOf(passwordField.getPassword());
				String firstName = tfFirstName.getText();
				String username = tfUsername.getText();
				
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setFirst_name(firstName);
					user.setRoleName(tfRole.getText());
				
					try {
						try {
							TransferClass transferClass = ControllerUI.getControllerUI().execute(
									TransferClass.create(user,ConstUI.USER,ConstBL.POST));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		}});
		
		btnRegistration.setBounds(220, 415, 252, 23);
		lblConfirmPassword.add(btnRegistration);
}
}
