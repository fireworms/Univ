package univ.ui;

import javax.swing.JDialog;

import univ.controll.StudentView;

public class StudentMain extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public StudentMain() {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		StudentView view = new StudentView(this);
		view.setGUIs();
	}

	public static void main(String[] args) {
		StudentMain a = new StudentMain();
		a.setVisible(true);
	}
}