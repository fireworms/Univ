package univ.ui;

import javax.swing.JDialog;

import univ.controll.CourseView;

public class CourseMain extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public CourseMain() {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		CourseView view = new CourseView(this);
		view.setGUIs();
	}

	public static void main(String[] args) {
		CourseMain a = new CourseMain();
		a.setVisible(true);
	}
}