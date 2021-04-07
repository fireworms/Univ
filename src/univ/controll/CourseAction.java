package univ.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import univ.dto.CourseData;
import univ.ui.CourseMain;

public class CourseAction {
	CourseMain main;
	CourseView view;
	JComponent[] check;

	public CourseAction(CourseMain main, CourseView view) {
		this.main = main;
		this.view = view;
		check = new JComponent[9];
		for (int i = 0; i < check.length; i++) {
			check[i] = view.courseComp[i];
		}
	}

	public void setListeners() {
		view.select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] conditionStr = { "�����ڵ�", "������", "�����⵵", "�����а�",
						"�����г�", "�����б�", "�����ü�", "��米��", "��������" };
				String[] targetStr = { "code", "subject", "openyear",
						"department", "opengrade", "semester", "hours",
						"professor", "score" };
				String condition = view.searchCondition.getSelectedItem()
						.toString();
				String searchText = null;
				for (int i = 0; i < conditionStr.length; i++) {
					if (condition.equals(conditionStr[i])) {
						condition = targetStr[i];
					}
				}
				try {
					searchText = view.searchBox.getText();
					ArrayList<CourseData> course = view.db.select(condition,
							searchText);
					view.dtm.setRowCount(0);
					for (int i = 0; i < course.size(); i++) {
						view.dtm.addRow(course.get(i).toArray());
					}
				} catch (Exception e) {
					System.out.println("���������� ����?");
				}
			}
		});

		view.selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					showTable();
				} catch (Exception e) {
					System.out.println("���������� ����?");
				}
			}
		});

		view.insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (nullCheck()) {
					try {
						String[] courseData = getCourseData();
						CourseData addcourse = new CourseData(courseData);
						view.db.insert(addcourse);
						showTable();
						clearField();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "��� ����");
						view.courseComp[0].requestFocus();
					}
				}
			}
		});

		view.remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.tableSelected) {
					try {
						if (view.tableSelected) {
							view.db.remove(view.table.getValueAt(view.index, 0)
									.toString());
							showTable();
							view.tableSelected = false;
							clearField();
						}
					} catch (Exception ee) {
						System.out.println("���������� ����?");
					}
				} else {
					String indexString = JOptionPane.showInputDialog(null,
							"���°�� �����ұ�", "�ٻ���", 0);
					if (indexString != null && indexString != "") {
						try {
							view.index = Integer.parseInt(indexString) - 1;
							String name = view.dtm.getValueAt(view.index, 0)
									.toString();
							view.db.remove(name);
							view.dtm.removeRow(view.index);
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, "�߸��� �Է�");
						}
					}
				}
			}
		});

		view.modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.isModifying) {
					if (nullCheck()) {
						if (JOptionPane.showConfirmDialog(null, "�����Ͻǰ̴ϱ��") == 0) {
							try {
								String[] courseData = getCourseData();
								CourseData modifycourse = new CourseData(courseData);
								view.db.modify(modifycourse);
								showTable();
								clearField();
								JTextField temp = (JTextField)view.courseComp[0];
								temp.setEditable(true);
								view.modify.setBackground(Color.yellow);
								view.modify.setForeground(Color.gray);
								view.tableSelected = false;
								view.isModifying = false;
							} catch (Exception ee) {
								System.out.println("���������� ����?");
							}
						}
					}
				} else {
					if (view.tableSelected) {
						view.isModifying = true;
						JTextField temp = (JTextField)view.courseComp[0];
						temp.setText(view.table.getValueAt(view.index, 0).toString());
						view.modify.setBackground(Color.LIGHT_GRAY);
						view.modify.setForeground(Color.black);
						temp.setEditable(false);
					}
				}
			}
		});

		view.exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.dispose();
			}
		});

		view.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				view.tableSelected = true;
				view.index = view.table.getSelectedRow();
				
				for (int i = 0; i < view.courseComp.length; i++) {
					if (i == 0 || i == 1 || i == 6 || i == 8) {
						JTextField temp = new JTextField();
						temp = (JTextField) check[i];
						temp.setText(view.table.getValueAt(view.index, i).toString());
					} else {
						JComboBox<String> temp = new JComboBox<String>();
						temp = (JComboBox) check[i];
						temp.setSelectedItem(view.table.getValueAt(view.index, i).toString());
					}
				}
			}
		});

		view.contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!arg0.getSource().equals(view.table)) {
					JTextField temp = (JTextField)view.courseComp[0];
					temp.setEditable(true);
					view.modify.setBackground(Color.yellow);
					view.modify.setForeground(Color.gray);
					view.tableSelected = false;
					view.isModifying = false;
				}
			}
		});
	}

	public boolean nullCheck() {
		for (int i = 0; i < check.length; i++) {
			if (i == 0 || i == 1 || i == 6 || i == 8) {
				JTextField temp = new JTextField();
				temp = (JTextField) check[i];
				if (temp.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
					temp.requestFocus();
					return false;
				}
			}
		}
		return true;
	}
	
	private String[] getCourseData(){
		String[] courseData = new String[9];
		for (int i = 0; i < check.length; i++) {
			if (i == 0 || i == 1 || i == 6 || i == 8) {
				JTextField temp = new JTextField();
				temp = (JTextField) check[i];
				courseData[i] = temp.getText();
			}else{
				JComboBox<String> temp = new JComboBox<String>();
				temp = (JComboBox) check[i];
				courseData[i] = temp.getSelectedItem().toString();
			}
		}
		return courseData;
	}

	private void showTable() throws Exception {
		ArrayList<CourseData> course = view.db.selectAll();
		view.dtm.setRowCount(0);
		for (int i = 0; i < course.size(); i++) {
			view.dtm.addRow(course.get(i).toArray());
		}
	}

	public void clearField() {
		for (int i = 0; i < check.length; i++) {
			if (i == 0 || i == 1 || i == 6 || i == 8) {
				JTextField temp = new JTextField();
				temp = (JTextField) check[i];
				temp.setText("");
			}
		}
	}
}
