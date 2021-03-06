package univ.ui;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import univ.controll.DepartmentAction;
import univ.style.Colors;

public class DepartmentMain extends JDialog {

	private static final long serialVersionUID = 1L;

	int width = 620;
	public Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JPanel pn1, pn2_1, pn2_2, pn2_3, pn3, pn4, pn6;
	public JScrollPane pn5;
	JLabel topLbl, depCodeLbl, depNameLbl, depMajorLbl;
	public JTextField depCodeTf;

	public JTextField depNameTf;

	public JTextField depMajorTf;

	public JTextField searchBox;
	public JComboBox<String> searchCondition;
	public JButton select;

	public JButton selectAll;

	public JButton insert;

	public JButton modify;

	public JButton remove;

	public JButton exit;
	public DefaultTableModel dtm;
	public JTable table;

	public DepartmentMain() {
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(width, 550));
		contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		pn1 = new JPanel();
		pn1.setMinimumSize(new Dimension(width, 45));
		pn1.setMaximumSize(new Dimension(width, 45));
		pn1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pn1.setBackground(Colors.getSubject());
		contentPane.add(pn1);

		pn2_1 = new JPanel();
		pn2_1.setMinimumSize(new Dimension(width, 45));
		pn2_1.setMaximumSize(new Dimension(width, 45));
		pn2_1.setLayout(new BoxLayout(pn2_1, BoxLayout.X_AXIS));
		pn2_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn2_1);

		pn2_2 = new JPanel();
		pn2_2.setMinimumSize(new Dimension(width, 45));
		pn2_2.setMaximumSize(new Dimension(width, 45));
		pn2_2.setLayout(new BoxLayout(pn2_2, BoxLayout.X_AXIS));
		pn2_2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn2_2);

		pn2_3 = new JPanel();
		pn2_3.setMinimumSize(new Dimension(width, 45));
		pn2_3.setMaximumSize(new Dimension(width, 45));
		pn2_3.setLayout(new BoxLayout(pn2_3, BoxLayout.X_AXIS));
		pn2_3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn2_3);

		pn4 = new JPanel();
		pn4.setMinimumSize(new Dimension(width, 70));
		pn4.setMaximumSize(new Dimension(width, 70));
		pn4.setLayout(new BoxLayout(pn4, BoxLayout.X_AXIS));
		pn4.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

		contentPane.add(pn4);

		String[] colNames = { "????????", "??????", "??????" };
		Object[][] rowData = {};
		dtm = new DefaultTableModel(rowData, colNames);
		table = new JTable(dtm);
		pn5 = new JScrollPane(table);
		pn5.setMinimumSize(new Dimension(width, 200));
		pn5.setMaximumSize(new Dimension(width, 200));
		contentPane.add(pn5);

		pn6 = new JPanel();
		pn6.setMinimumSize(new Dimension(width, 50));
		pn6.setMaximumSize(new Dimension(width, 50));
		pn6.setLayout(new GridLayout(0, 4, 10, 10));
		pn6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn6);

		topLbl = new JLabel("????/???? ????");
		pn1.add(topLbl);

		depCodeLbl = new JLabel("???????? ");
		pn2_1.add(depCodeLbl);
		depCodeTf = new JTextField();
		pn2_1.add(depCodeTf);

		depNameLbl = new JLabel(" ?????? ");
		pn2_1.add(depNameLbl);
		depNameTf = new JTextField();
		pn2_1.add(depNameTf);

		depMajorLbl = new JLabel(" ?????? ");
		pn2_1.add(depMajorLbl);
		depMajorTf = new JTextField();
		pn2_1.add(depMajorTf);

		String[] conditions = { "????????", "??????", "??????" };
		searchCondition = new JComboBox<String>(conditions);
		searchCondition.setPreferredSize(new Dimension(100, 20));
		pn4.add(searchCondition);
		pn4.add(emptyLbl1);

		searchBox = new JTextField();
		pn4.add(searchBox);
		pn4.add(emptyLbl2);

		select = new JButton("????");
		select.setForeground(Colors.getBtnTxt());
		select.setBackground(Colors.getBtn());   
		pn4.add(select);
		pn4.add(emptyLbl3);

		selectAll = new JButton("????????");
		selectAll.setForeground(Colors.getBtnTxt());
		selectAll.setBackground(Colors.getBtn());   
		pn4.add(selectAll);

		insert = new JButton("????");
		insert.setForeground(Colors.getBtnTxt());
		insert.setBackground(Colors.getBtn());
		pn6.add(insert);
		modify = new JButton("????");
		modify.setForeground(Colors.getBtnTxt());
		modify.setBackground(Colors.getBtn());   
		pn6.add(modify);
		remove = new JButton("????");
		remove.setForeground(Colors.getBtnTxt());
		remove.setBackground(Colors.getBtn());   
		pn6.add(remove);
		exit = new JButton("????");
		exit.setForeground(Colors.getBtnTxt());
		exit.setBackground(Colors.getBtn());   
		pn6.add(exit);

		new DepartmentAction(this);

	}

	public static void main(String[] args) {
		DepartmentMain a = new DepartmentMain();
		a.setVisible(true);
	}

}