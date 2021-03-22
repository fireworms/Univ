package department.controll;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import department.dao.DepDAO;

public class DepartmentView {
	DepartmentMain view;
	int width = 620;
	Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JButton bn;
	JPanel pn1, pn2_1, pn2_2, pn2_3, pn3, pn4, pn6;
	JScrollPane pn5;
	JLabel topLbl, depCodeLbl, depNameLbl, depMajorLbl;
	JTextField depCodeTf, depNameTf, depMajorTf, searchBox;
	JComboBox<String> searchCondition;
	JButton select, selectAll, insert, modify, remove, exit;
	DefaultTableModel dtm;
	JTable table;
	int index = 0;
	DepDAO db = new DepDAO("major");
	boolean tableSelected = false;
	boolean isModifying = false;
	
	public DepartmentView(DepartmentMain view){
		this.view = view;
	}
	
	public void setGUIs() {
		view.setSize(new Dimension(width, 550));
		contentPane = view.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		pn1 = new JPanel();
		pn1.setMinimumSize(new Dimension(width, 45));
		pn1.setMaximumSize(new Dimension(width, 45));
		pn1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pn1.setBackground(Color.gray);
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
		pn4.setBackground(Color.blue);
		contentPane.add(pn4);

		String[] colNames = { "학과코드", "학과명", "전공명" };
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

		topLbl = new JLabel("학과/전공 관리");
		pn1.add(topLbl);

		depCodeLbl = new JLabel("학과코드 ");
		pn2_1.add(depCodeLbl);
		depCodeTf = new JTextField();
		pn2_1.add(depCodeTf);

		depNameLbl = new JLabel(" 학과명 ");
		pn2_1.add(depNameLbl);
		depNameTf = new JTextField();
		pn2_1.add(depNameTf);

		depMajorLbl = new JLabel(" 전공명 ");
		pn2_1.add(depMajorLbl);
		depMajorTf = new JTextField();
		pn2_1.add(depMajorTf);

		String[] conditions = { "학과코드", "학과명", "전공명" };
		searchCondition = new JComboBox<String>(conditions);
		searchCondition.setPreferredSize(new Dimension(100, 20));
		pn4.add(searchCondition);
		pn4.add(emptyLbl1);

		searchBox = new JTextField();
		pn4.add(searchBox);
		pn4.add(emptyLbl2);

		select = new JButton("조회");
		pn4.add(select);
		pn4.add(emptyLbl3);

		selectAll = new JButton("전체조회");
		pn4.add(selectAll);

		insert = new JButton("등록");
		insert.setForeground(Color.white);
		insert.setBackground(Color.blue);
		pn6.add(insert);
		modify = new JButton("수정");
		modify.setForeground(Color.gray);
		modify.setBackground(Color.yellow);
		pn6.add(modify);
		remove = new JButton("삭제");
		remove.setForeground(Color.white);
		remove.setBackground(Color.red);
		pn6.add(remove);
		exit = new JButton("종료");
		exit.setForeground(Color.white);
		exit.setBackground(Color.black);
		pn6.add(exit);
		
		DepartmentAction controll = new DepartmentAction(view);
		controll.setListeners(this);
	}

}
