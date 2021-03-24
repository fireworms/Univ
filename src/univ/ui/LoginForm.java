package univ.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForm extends JFrame{
	
	Container contentPane;
	JPanel topPanel, centerPanel, botPanel;
	JPanel innerTop, innerCenter1, innerCenter2, innerBot1, innerBot2, innerBot3;
	JLabel topLbl, idLbl, passLbl;
	JButton reg, login, exit;
	JTextField idTF, passTF;
	
	public LoginForm(){
		this.setSize(new Dimension(400, 260));
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setBackground(Color.black);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));
		contentPane.add(topPanel);
		contentPane.add(centerPanel);
		contentPane.add(botPanel);
		
		innerTop = new JPanel();
		innerTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter1 = new JPanel();
		innerCenter1.setMinimumSize(new Dimension(150, 70));
		innerCenter1.setMaximumSize(new Dimension(150, 70));
		innerCenter1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter1.setLayout(new GridLayout(0, 1, 10, 10));
		innerCenter2 = new JPanel();
		innerCenter2.setMinimumSize(new Dimension(250, 70));
		innerCenter2.setMaximumSize(new Dimension(250, 70));
		innerCenter2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter2.setLayout(new GridLayout(0, 1, 10, 10));
		innerBot1 = new JPanel();
		innerBot1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerBot2 = new JPanel();
		innerBot2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerBot3 = new JPanel();
		innerBot3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.add(innerTop);
		centerPanel.add(innerCenter1);
		centerPanel.add(innerCenter2);
		botPanel.add(innerBot1);
		botPanel.add(innerBot2);
		botPanel.add(innerBot3);
		
		topLbl = new JLabel("�л���� ���α׷�"); 
		idLbl = new JLabel("ID");
		passLbl = new JLabel("Password");
		innerTop.add(topLbl);
		innerCenter1.add(idLbl);
		innerCenter1.add(passLbl);
		
		idTF = new JTextField();
		passTF = new JTextField();
		innerCenter2.add(idTF);
		innerCenter2.add(passTF);
		
		reg = new JButton("��  ��");
		login = new JButton("�α���");
		exit = new JButton("��  ��");
		
		innerBot1.add(reg);
		innerBot2.add(login);
		innerBot3.add(exit);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		LoginForm aa = new LoginForm();
	}

}
