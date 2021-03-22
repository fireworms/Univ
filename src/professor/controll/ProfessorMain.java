package professor.controll;

import javax.swing.JFrame;

public class ProfessorMain extends JFrame {

	private static final long serialVersionUID = 1L;

	ProfessorMain() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ProfessorView view = new ProfessorView(this);
		view.setGUIs();
	}

	public static void main(String[] args) {
		ProfessorMain a = new ProfessorMain();
		a.setVisible(true);
	}
}