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

import univ.dao.StuDAO;
import univ.ui.StudentMain;

public class StudentView {
	StudentMain view;
	int width = 820;
	Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JButton bn;
	JPanel pn1, pn2_1, pn2_2, pn2_3, pn2_4, pn2_5, pn3, pn4, pn6;
	JScrollPane pn5;
	JLabel topLbl, stuCodeLbl, stuNameLbl, stuJuminNum_1Lbl, stuJuminNum_2Lbl,
			stuAddrLbl, stuCellphoneLbl, stuPhoneLbl, stuEntYearLbl,
			stuHighSchoolLbl, stuGradLbl, stuDepCodeLbl, stuProCodeLbl;
	JTextField stuCodeTf, stuNameTf, stuJuminNum_1Tf, stuJuminNum_2Tf,
			stuAddrTf, stuCellphoneTf, stuPhoneTf, stuEntYearTf, stuHighSchoolTf,
			stuGradTf, stuDepCodeTf, stuProCodeTf, searchBox;
	JComboBox<String> searchCondition;
	JButton select, selectAll, insert, modify, remove, exit;
	DefaultTableModel dtm;
	JTable table;
	int index = 0;
	StuDAO db = new StuDAO("Student");
	String[] conditions = { "학 번", "이 름", "주 소", "주민등록번호", "휴대폰", "전 화",
			"입학년도", "졸업고교", "고교졸업년도", "학과/전공" , "지도교수"};
	boolean tableSelected = false;
	boolean isModifying = false;

	public StudentView(StudentMain view) {
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
		pn2_5.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 340));
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

		stuCodeLbl = new JLabel("학 번 ");
		pn2_1.add(stuCodeLbl);
		stuCodeTf = new JTextField();
		pn2_1.add(stuCodeTf);

		stuNameLbl = new JLabel(" 이 름 ");
		pn2_1.add(stuNameLbl);
		stuNameTf = new JTextField();
		pn2_1.add(stuNameTf);

		stuJuminNum_1Lbl = new JLabel(" 주민번호 ");
		pn2_1.add(stuJuminNum_1Lbl);
		stuJuminNum_1Tf = new JTextField();
		pn2_1.add(stuJuminNum_1Tf);

		stuJuminNum_2Lbl = new JLabel(" - ");
		pn2_1.add(stuJuminNum_2Lbl);
		stuJuminNum_2Tf = new JTextField();
		pn2_1.add(stuJuminNum_2Tf);

		stuAddrLbl = new JLabel("주    소 ");
		pn2_2.add(stuAddrLbl);
		stuAddrTf = new JTextField();
		pn2_2.add(stuAddrTf);

		stuCellphoneLbl = new JLabel("휴대폰 ");
		pn2_3.add(stuCellphoneLbl);
		stuCellphoneTf = new JTextField();
		pn2_3.add(stuCellphoneTf);

		stuPhoneLbl = new JLabel(" 전 화 ");
		pn2_3.add(stuPhoneLbl);
		stuPhoneTf = new JTextField();
		pn2_3.add(stuPhoneTf);

		stuEntYearLbl = new JLabel("입학년도 ");
		pn2_4.add(stuEntYearLbl);
		stuEntYearTf = new JTextField();
		pn2_4.add(stuEntYearTf);

		stuHighSchoolLbl = new JLabel("      졸업고교 ");
		pn2_4.add(stuHighSchoolLbl);
		stuHighSchoolTf = new JTextField();
		pn2_4.add(stuHighSchoolTf);

		stuGradLbl = new JLabel(" 고교졸업년도 ");
		pn2_4.add(stuGradLbl);
		stuGradTf = new JTextField();
		pn2_4.add(stuGradTf);

		stuDepCodeLbl = new JLabel("학과/전공 ");
		pn2_5.add(stuDepCodeLbl);
		stuDepCodeTf = new JTextField();
		pn2_5.add(stuDepCodeTf);
		
		stuProCodeLbl = new JLabel("지도교수 ");
		pn2_5.add(stuProCodeLbl);
		stuProCodeTf = new JTextField();
		pn2_5.add(stuProCodeTf);
		
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

		StudentAction controll = new StudentAction(view, this);
		controll.setListeners();
	}

}
