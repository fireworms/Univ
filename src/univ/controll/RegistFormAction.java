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
		if(auth.getSelectedItem().toString().equals("학생")) {authCode = "s";}
		else if(auth.getSelectedItem().toString().equals("교수")) {authCode = "p";} 
		loginData.setCode(authCode + codeTF.getText());
		RegistFormService RegistFormService = new RegistFormService();
		int isSuccess = RegistFormService.registUser(loginData);
		if (isSuccess == 1) {
			JOptionPane.showMessageDialog(null, "등록 성공");
			view.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "등록 실패");
		}
	}
	
	private boolean valCheck(){
		if(this.idTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
			this.idTF.requestFocus();
			return false;
		}
		if(this.passTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "패스워드를 입력하세요");
			this.passTF.requestFocus();
			return false;
		}
		
		if(this.codeTF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "학생/교수번호를 입력하세요");
			this.codeTF.requestFocus();
			return false;
		}
		return true;
	}
}
