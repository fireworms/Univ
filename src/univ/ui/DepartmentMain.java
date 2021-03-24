package univ.ui;

import javax.swing.JDialog;

import univ.controll.DepartmentView;

public class DepartmentMain extends JDialog {

	private static final long serialVersionUID = 1L;

	public DepartmentMain() {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		DepartmentView view = new DepartmentView(this);
		view.setGUIs();
		
	}

	public static void main(String[] args) {
		DepartmentMain a = new DepartmentMain();
		a.setVisible(true);
	}

}