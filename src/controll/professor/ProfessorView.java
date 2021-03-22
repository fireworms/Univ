package controll.professor;

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

import dao.professor.ProDAO;

public class ProfessorView {
	ProfessorMain view;
	int width = 820;
	Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JButton bn;
	JPanel pn1, pn2_1, pn2_2, pn2_3, pn2_4, pn2_5, pn3, pn4, pn6;
	JScrollPane pn5;
	JLabel topLbl, proCodeLbl, proNameLbl, proJuminNum_1Lbl, proJuminNum_2Lbl,
			proAddrLbl, proCellphoneLbl, proPhoneLbl, proHireYearLbl,
			proDegreeLbl, proMajorLbl, proLabLbl;
	JTextField proCodeTf, proNameTf, proJuminNum_1Tf, proJuminNum_2Tf,
			proAddrTf, proCellphoneTf, proPhoneTf, proHireYearTf, proDegreeTf,
			proMajorTf, proLabTf, searchBox;
	JComboBox<String> searchCondition;
	JButton select, selectAll, insert, modify, remove, exit;
	DefaultTableModel dtm;
	JTable table;
	int index = 0;
	ProDAO db = new ProDAO("professor");
	String[] conditions = { "교수코드", "이 름", "주 소", "주민등록번호", "휴대폰", "전 화",
			"임용년도", "학 위", "학과/전공", "연구실" };
	boolean tableSelected = false;
	boolean isModifying = false;

	public ProfessorView(ProfessorMain view) {
		this.view = view;
	}

	public void setGUIs() {
		view.setSize(new Dimension(width, 650));
		contentPane = view.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		pn1 = new JPanel();
		pn1.setMinimumSize(new Dimension(width, 45));
		pn1.setMaximumSize(new Dimension(width, 45));
		pn1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pn1.setBackground(Color.gray);
		contentPane.add(pn1);

		pn2_1 = new JPanel();
		pn2_1.setMinimumSize(new Dimension(width, 40));
		pn2_1.setMaximumSize(new Dimension(width, 40));
		pn2_1.setLayout(new BoxLayout(pn2_1, BoxLayout.X_AXIS));
		pn2_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		contentPane.add(pn2_1);

		pn2_2 = new JPanel();
		pn2_2.setMinimumSize(new Dimension(width, 35));
		pn2_2.setMaximumSize(new Dimension(width, 35));
		pn2_2.setLayout(new BoxLayout(pn2_2, BoxLayout.X_AXIS));
		pn2_2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		contentPane.add(pn2_2);

		pn2_3 = new JPanel();
		pn2_3.setMinimumSize(new Dimension(width, 35));
		pn2_3.setMaximumSize(new Dimension(width, 35));
		pn2_3.setLayout(new BoxLayout(pn2_3, BoxLayout.X_AXIS));
		pn2_3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 140));
		contentPane.add(pn2_3);

		pn2_4 = new JPanel();
		pn2_4.setMinimumSize(new Dimension(width, 35));
		pn2_4.setMaximumSize(new Dimension(width, 35));
		pn2_4.setLayout(new BoxLayout(pn2_4, BoxLayout.X_AXIS));
		pn2_4.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		contentPane.add(pn2_4);

		pn2_5 = new JPanel();
		pn2_5.setMinimumSize(new Dimension(width, 40));
		pn2_5.setMaximumSize(new Dimension(width, 40));
		pn2_5.setLayout(new BoxLayout(pn2_5, BoxLayout.X_AXIS));
		pn2_5.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 640));
		contentPane.add(pn2_5);

		pn4 = new JPanel();
		pn4.setMinimumSize(new Dimension(width, 70));
		pn4.setMaximumSize(new Dimension(width, 70));
		pn4.setLayout(new BoxLayout(pn4, BoxLayout.X_AXIS));
		pn4.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		pn4.setBackground(Color.blue);
		contentPane.add(pn4);

		String[] colNames = conditions;
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

		topLbl = new JLabel("교 수 관 리");
		pn1.add(topLbl);

		proCodeLbl = new JLabel("교수코드 ");
		pn2_1.add(proCodeLbl);
		proCodeTf = new JTextField();
		pn2_1.add(proCodeTf);

		proNameLbl = new JLabel(" 이 름 ");
		pn2_1.add(proNameLbl);
		proNameTf = new JTextField();
		pn2_1.add(proNameTf);

		proJuminNum_1Lbl = new JLabel(" 주민번호 ");
		pn2_1.add(proJuminNum_1Lbl);
		proJuminNum_1Tf = new JTextField();
		pn2_1.add(proJuminNum_1Tf);

		proJuminNum_2Lbl = new JLabel(" - ");
		pn2_1.add(proJuminNum_2Lbl);
		proJuminNum_2Tf = new JTextField();
		pn2_1.add(proJuminNum_2Tf);

		proAddrLbl = new JLabel("주    소 ");
		pn2_2.add(proAddrLbl);
		proAddrTf = new JTextField();
		pn2_2.add(proAddrTf);

		proCellphoneLbl = new JLabel("휴대폰 ");
		pn2_3.add(proCellphoneLbl);
		proCellphoneTf = new JTextField();
		pn2_3.add(proCellphoneTf);

		proPhoneLbl = new JLabel(" 전 화 ");
		pn2_3.add(proPhoneLbl);
		proPhoneTf = new JTextField();
		pn2_3.add(proPhoneTf);

		proHireYearLbl = new JLabel("임용년도 ");
		pn2_4.add(proHireYearLbl);
		proHireYearTf = new JTextField();
		pn2_4.add(proHireYearTf);

		proDegreeLbl = new JLabel("      학 위 ");
		pn2_4.add(proDegreeLbl);
		proDegreeTf = new JTextField();
		pn2_4.add(proDegreeTf);

		proMajorLbl = new JLabel(" 학과 / 전공 ");
		pn2_4.add(proMajorLbl);
		proMajorTf = new JTextField();
		pn2_4.add(proMajorTf);

		proLabLbl = new JLabel("연구실 ");
		pn2_5.add(proLabLbl);
		proLabTf = new JTextField();
		pn2_5.add(proLabTf);
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

		ProfessorAction controll = new ProfessorAction(view, this);
		controll.setListeners();
	}

}
