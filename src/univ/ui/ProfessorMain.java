package univ.ui;

import javax.swing.JDialog;

import univ.controll.ProfessorView;

public class ProfessorMain extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public ProfessorMain() {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ProfessorView view = new ProfessorView(this);
		view.setGUIs();
	}

	public static void main(String[] args) {
		ProfessorMain a = new ProfessorMain();
		a.setVisible(true);
	}
}