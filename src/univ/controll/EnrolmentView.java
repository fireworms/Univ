package univ.controll;

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

import univ.dao.EnrolmentDAO;
import univ.style.Colors;
import univ.ui.EnrolmentMain;

public class EnrolmentView {
	EnrolmentMain view;
	String userId;
	int width = 620;
	Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JPanel pn1, pn2, pn4, pn6;
	JScrollPane pn3, pn5 ;
	JLabel topLbl;
	JTextField searchBox;
	JComboBox<String> searchCondition;
	JButton select, selectAll, insert, remove, confirm, exit;
	DefaultTableModel dtm, enrolDtm;
	JTable table, enrolTable;
	int index = 0;
	int enrolIndex = 0;
	EnrolmentDAO db = new EnrolmentDAO("course");
	String[] conditions = { "교과코드", "교과명", "개설학과", "수업시수", "담당교수", "개설학점" };
	boolean tableSelected = false;
	boolean enrolTableSelected = false;

	public EnrolmentView(EnrolmentMain view, String userId) {
		this.view = view;
		this.userId = userId;
	}

	public void setGUIs() {
		view.setTitle("사용자 : " + userId);
		view.setSize(new Dimension(width, 650));
		contentPane = view.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		pn1 = new JPanel();
		pn1.setMinimumSize(new Dimension(width, 45));
		pn1.setMaximumSize(new Dimension(width, 45));
		pn1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pn1.setBackground(Colors.getSubject());
		contentPane.add(pn1);

		pn2 = new JPanel();
		pn2.setMinimumSize(new Dimension(width, 70));
		pn2.setMaximumSize(new Dimension(width, 70));
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
		pn2.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		contentPane.add(pn2);

		String[] colNames = conditions;
		Object[][] rowData = {};
		dtm = new DefaultTableModel(rowData, colNames);
		table = new JTable(dtm);
		pn3 = new JScrollPane(table);
		pn3.setMinimumSize(new Dimension(width, 200));
		pn3.setMaximumSize(new Dimension(width, 200));
		contentPane.add(pn3);

		pn4 = new JPanel();
		pn4.setMinimumSize(new Dimension(width, 50));
		pn4.setMaximumSize(new Dimension(width, 50));
		pn4.setLayout(new GridLayout(0, 4, 10, 10));
		pn4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn4);
		
		String[] enrolColNames = conditions;
		Object[][] enrolRowData = {};
		enrolDtm = new DefaultTableModel(enrolRowData, enrolColNames);
		enrolTable = new JTable(enrolDtm);
		pn3 = new JScrollPane(enrolTable);
		pn3.setMinimumSize(new Dimension(width, 200));
		pn3.setMaximumSize(new Dimension(width, 200));
		contentPane.add(pn3);
		
		pn6 = new JPanel();
		pn6.setMinimumSize(new Dimension(width, 50));
		pn6.setMaximumSize(new Dimension(width, 50));
		pn6.setLayout(new GridLayout(0, 4, 10, 10));
		pn6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(pn6);

		topLbl = new JLabel("수 강 신 청");
		pn1.add(topLbl);

		searchCondition = new JComboBox<String>(conditions);
		searchCondition.setPreferredSize(new Dimension(100, 20));
		pn2.add(searchCondition);
		pn2.add(emptyLbl1);

		searchBox = new JTextField();
		pn2.add(searchBox);
		pn2.add(emptyLbl2);

		select = new JButton("조회");
		select.setForeground(Colors.getBtnTxt());
		select.setBackground(Colors.getBtn());   
		pn2.add(select);
		pn2.add(emptyLbl3);

		selectAll = new JButton("전체조회");
		selectAll.setForeground(Colors.getBtnTxt());
		selectAll.setBackground(Colors.getBtn());   
		pn2.add(selectAll);

		remove = new JButton("▲");
		remove.setForeground(Colors.getBtnTxt());
		remove.setBackground(Colors.getBtn());
		pn4.add(remove);
		
		insert = new JButton("▼");
		insert.setForeground(Colors.getBtnTxt());
		insert.setBackground(Colors.getBtn());   
		pn4.add(insert);
		
		confirm = new JButton("저장");
		confirm.setForeground(Colors.getBtnTxt());
		confirm.setBackground(Colors.getBtn());   
		pn6.add(confirm);
		
		exit = new JButton("종료");
		exit.setForeground(Colors.getBtnTxt());
		exit.setBackground(Colors.getBtn());   
		pn6.add(exit);

		EnrolmentAction controll = new EnrolmentAction(view, this);
		controll.setListeners();
	}

}
