package univ.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import univ.dto.LoginData;
import univ.svc.LoginFormService;
import univ.ui.LoginForm;
import univ.ui.RegistForm;
import univ.ui.UnivMainMenu;

public class LoginFormAction {

	JButton reg, login, exit;
	JTextField idTF, passTF;
	LoginForm view;
	LoginData getLoginData;

	public LoginFormAction(LoginForm view) {
		this.view = view;
		this.idTF = view.idTF;
		this.passTF = view.passTF;
		this.reg = view.reg;
		this.login = view.login;
		this.exit = view.exit;

		passTF.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10) {
					loginAction();
				}
			}
		});

		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistForm();
			}
		});

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(nullCheck()){
					loginAction();
				}
			}

		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
	}

	private void openMain() {
		view.setVisible(false);
		UnivMainMenu aa = new UnivMainMenu(getLoginData);
		aa.setVisible(true);
	}

	private void loginAction() {
		LoginData loginData = new LoginData();
		loginData.setId(idTF.getText());
		loginData.setPassword(passTF.getText());
		LoginFormService loginFormService = new LoginFormService();
		try {
			getLoginData = loginFormService.loginCheck(loginData);
			if (getLoginData.getCode().equals("admin")) {
				JOptionPane.showMessageDialog(null, "������ �α���");
				openMain();
			} else if (getLoginData.getCode().charAt(0) == 'p') {
				JOptionPane.showMessageDialog(null,
						"���� " + getLoginData.getName() + " �α���");
				openMain();
			} else if (getLoginData.getCode().charAt(0) == 's') {
				JOptionPane.showMessageDialog(null,
						"�л� " + getLoginData.getName() + " �α���");
				openMain();
			}
		} catch (Exception ee) {
			alert();
			ee.printStackTrace();
		}
	}
	
	private void alert(){
		JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� ����� �Է��ϼ���");
	}
	
	private boolean nullCheck(){
		if(this.idTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���");
			this.idTF.requestFocus();
			return false;
		}
		if(this.passTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "�н����带 �Է��ϼ���");
			this.passTF.requestFocus();
			return false;
		}
		return true;
	}
}
