package univ.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import univ.dto.LoginData;
import univ.svc.RegistFormService;
import univ.ui.RegistForm;

public class RegistFormAction {

	JButton reg, login, exit;
	JTextField idTF, passTF, codeTF;
	JComboBox<String> auth;
	RegistForm view;

	public RegistFormAction(RegistForm view) {
		this.view = view;
		this.idTF = view.idTF;
		this.passTF = view.passTF;
		this.codeTF = view.codeTF;
		this.reg = view.reg;
		this.exit = view.exit;
		this.auth = view.auth;

		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(valCheck()){
					regist();
				}
			}
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}

		});
	}
	
	private void regist(){
		LoginData loginData = new LoginData();
		loginData.setId(idTF.getText());
		loginData.setPassword(passTF.getText());
		String authCode = null;
		if(auth.getSelectedItem().toString().equals("�л�")) {authCode = "s";}
		else if(auth.getSelectedItem().toString().equals("����")) {authCode = "p";} 
		loginData.setCode(authCode + codeTF.getText());
		RegistFormService RegistFormService = new RegistFormService();
		int isSuccess = RegistFormService.registUser(loginData);
		if (isSuccess == 1) {
			JOptionPane.showMessageDialog(null, "��� ����");
			view.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "��� ����");
		}
	}
	
	private boolean valCheck(){
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
		
		if(this.codeTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "�л�/������ȣ�� �Է��ϼ���");
			this.codeTF.requestFocus();
			return false;
		}
		return true;
	}
}
