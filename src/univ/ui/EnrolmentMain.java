package univ.ui;

import javax.swing.JDialog;

import univ.controll.EnrolmentView;

public class EnrolmentMain extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public EnrolmentMain(String userId) {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		EnrolmentView view = new EnrolmentView(this, userId);
		view.setGUIs();
	}
}