package department.controll;

import javax.swing.JFrame;

public class DepartmentMain extends JFrame {

	private static final long serialVersionUID = 1L;

	DepartmentMain() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		DepartmentView view = new DepartmentView(this);
		view.setGUIs();
	}

	public static void main(String[] args) {
		DepartmentMain a = new DepartmentMain();
		a.setVisible(true);
	}

}