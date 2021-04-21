package univ.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import univ.controll.RegistFormAction;
import univ.style.Colors;

public class RegistForm extends JDialog{
	
	private static final long serialVersionUID = 1201267056037992002L;
	
	Container contentPane;
	JPanel topPanel, centerPanel, botPanel;
	JPanel innerTop, innerCenter1, innerCenter1_code, innerCenter2, innerBot1, innerBot2;
	JLabel topLbl, idLbl, passLbl, codeLbl;
	public JButton reg, exit;
	public JTextField idTF, passTF, codeTF;
	public JComboBox<String> auth;
	
	public RegistForm(){
		this.setTitle("가입");
		this.setSize(new Dimension(400, 290));
		this.setModal(true);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));
		contentPane.add(topPanel);
		contentPane.add(centerPanel);
		contentPane.add(botPanel);
		
		innerTop = new JPanel();
		innerTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter1 = new JPanel();
		innerCenter1.setMinimumSize(new Dimension(150, 105));
		innerCenter1.setMaximumSize(new Dimension(150, 105));
		innerCenter1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter1.setLayout(new GridLayout(0, 1, 10, 10));
		innerCenter1_code = new JPanel();
		innerCenter1_code.setLayout(new GridLayout(0, 2));
		innerCenter2 = new JPanel();
		innerCenter2.setMinimumSize(new Dimension(250, 105));
		innerCenter2.setMaximumSize(new Dimension(250, 105));
		innerCenter2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerCenter2.setLayout(new GridLayout(0, 1, 10, 10));
		innerBot1 = new JPanel();
		innerBot1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerBot2 = new JPanel();
		innerBot2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.add(innerTop);
		centerPanel.add(innerCenter1);
		centerPanel.add(innerCenter2);
		botPanel.add(innerBot1);
		botPanel.add(innerBot2);
		
		topLbl = new JLabel("회 원 가 입"); 
		idLbl = new JLabel("ID");
		passLbl = new JLabel("Password");
		auth = new JComboBox<String>();
		auth.addItem("학생");
		auth.addItem("교수");
		codeLbl = new JLabel("번호");
		innerTop.add(topLbl);
		innerCenter1.add(idLbl);
		innerCenter1.add(passLbl);
		innerCenter1.add(innerCenter1_code);
		innerCenter1_code.add(auth);
		innerCenter1_code.add(codeLbl);
		
		
		idTF = new JTextField();
		passTF = new JTextField();
		codeTF = new JTextField();
		innerCenter2.add(idTF);
		innerCenter2.add(passTF);
		innerCenter2.add(codeTF);
		
		reg = new JButton("가  입");
		reg.setForeground(Colors.getBtnTxt());
		reg.setBackground(Colors.getBtn());
		exit = new JButton("종  료");
		exit.setForeground(Colors.getBtnTxt());
		exit.setBackground(Colors.getBtn());   
		innerBot1.add(reg);
		innerBot2.add(exit);
		
		new RegistFormAction(this);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
