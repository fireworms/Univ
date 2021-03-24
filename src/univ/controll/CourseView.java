package univ.controll;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import univ.dao.CourseDAO;
import univ.ui.CourseMain;

public class CourseView {
	CourseMain view;
	int width = 620;
	Container contentPane;
	JLabel emptyLbl1 = new JLabel("  ");
	JLabel emptyLbl2 = new JLabel("  ");
	JLabel emptyLbl3 = new JLabel("  ");
	JButton bn;
	JPanel pn1, pn2_1, pn2_2, pn2_3, pn2_4, pn2_5, pn3, pn4, pn6;
	JScrollPane pn5;
	JLabel topLbl;
	JLabel[] courseLbl = new JLabel[9];
	JComponent[] courseComp = new JComponent[9];
	JTextField searchBox;
	JTextField[] courseTF  = new JTextField[4];
	JComboBox<String> searchCondition;
	JComboBox<String>[] courseCB  = new JComboBox[5]; 
	JButton select, selectAll, insert, modify, remove, exit;
	DefaultTableModel dtm;
	JTable table;
	int index = 0;
	CourseDAO db = new CourseDAO("course");
	String[] conditions = { "교과코드", "교과명", "개설년도", "개설학과", "개설학년", "개설학기",
			"수업시수", "담당교수", "개설학점" };
	String[][] boxStr = {
			{},
			{},
			{"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"},
			{"미용학과", "인공지능", "소프트웨어"},
			{"1학년", "2학년", "3학년", "4학년"},
			{"1학기", "2학기"},
			{},
			{"김두리", "이하나", "김하나", "정교수"},
			{}
	};
	boolean tableSelected = false;
	boolean isModifying = false;

	public CourseView(CourseMain view) {
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
		pn2_3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
		
		for(int i = 0; i < courseLbl.length; i++){
			courseLbl[i] = new JLabel();
			courseLbl[i].setText(" " + conditions[i] + " ");
			
			if(i == 0 || i == 1 || i == 6 || i == 8){
				courseComp[i] = new JTextField();
			}else{
				courseComp[i] = new JComboBox<String>(boxStr[i]);
			}
		}
		
		for(int i = 0; i < courseLbl.length; i++){
			if(i / 3 == 0){
				pn2_1.add(courseLbl[i]);
				pn2_1.add(courseComp[i]);
			}
			if(i / 3 == 1){
				pn2_2.add(courseLbl[i]);
				pn2_2.add(courseComp[i]);
			}
			if(i / 3 == 2){
				pn2_3.add(courseLbl[i]);
				pn2_3.add(courseComp[i]);
			}
		}
		

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

		topLbl = new JLabel("교 과 목 관 리");
		pn1.add(topLbl);

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
		
		CourseAction controll = new CourseAction(view, this);
		controll.setListeners();
	}

}
