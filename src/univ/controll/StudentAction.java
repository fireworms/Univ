package univ.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import univ.dto.StuData;
import univ.style.Colors;
import univ.ui.StudentMain;

public class StudentAction {
	StudentMain main;
	StudentView view;
	JTextField[] check;

	public StudentAction(StudentMain main, StudentView view) {
		this.main = main;
		this.view = view;
		check = new JTextField[11];
		check[0] = view.stuCodeTf;
		check[1] = view.stuNameTf;
		check[2] = view.stuJuminNum_1Tf;
		check[3] = view.stuJuminNum_2Tf;
		check[4] = view.stuAddrTf;
		check[5] = view.stuCellphoneTf;
		check[6] = view.stuPhoneTf;
		check[7] = view.stuEntYearTf;
		check[8] = view.stuHighSchoolTf;
		check[9] = view.stuGradTf;
		check[10] = view.stuDepCodeTf;
	}

	public void setListeners() {
		view.select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] conditionStr = { "학 번", "이 름", "주 소", "주민등록번호",
						"휴대폰", "전 화", "입학년도", "졸업고교", "고교졸업년도", "학과/전공", "지도교수"};
				String[] targetStr = { "code", "name", "addr", "juminnum",
						"cellphone", "phone", "entyear", "highschool",
						"gradyear", "depcode", "procode" };
				String condition = view.searchCondition.getSelectedItem()
						.toString();
				String searchText = null;
				for(int i = 0; i < conditionStr.length; i++){
					if (condition.equals(conditionStr[i])) {
						condition = targetStr[i];
					}
				}
				try {
					searchText = view.searchBox.getText();
					ArrayList<StuData> stu = view.db.select(condition,
							searchText);
					view.dtm.setRowCount(0);
					for (int i = 0; i < stu.size(); i++) {
						view.dtm.addRow(stu.get(i).toArray());
					}
				} catch (Exception e) {
					System.out.println("오류같은걸 끼얹나?");
				}
			}
		});

		view.selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					showTable();
				} catch (Exception e) {
					System.out.println("오류같은걸 끼얹나?");
				}
			}
		});

		view.insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (nullCheck()) {
					try {
						StuData addstu = new StuData(view.stuCodeTf.getText(),
								view.stuNameTf.getText(),
								view.stuJuminNum_1Tf.getText(),
								view.stuJuminNum_2Tf.getText(),
								view.stuAddrTf.getText(),
								view.stuCellphoneTf.getText(),
								view.stuPhoneTf.getText(),
								view.stuEntYearTf.getText(),
								view.stuHighSchoolTf.getText(),
								view.stuGradTf.getText(),
								view.stuDepCodeTf.getText(),
								view.stuProCodeTf.getText());
						view.db.insert(addstu);
						showTable();
						clearField();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "등록 실패");
						view.stuCodeTf.requestFocus();
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
						System.out.println("오류같은걸 끼얹나?");
					}
				} else {
					String indexString = JOptionPane.showInputDialog(null,
							"몇번째줄 삭제할까", "줄삭제", 0);
					if (indexString != null && indexString != "") {
						try {
							view.index = Integer.parseInt(indexString) - 1;
							String name = view.dtm.getValueAt(view.index, 0)
									.toString();
							view.db.remove(name);
							view.dtm.removeRow(view.index);
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, "잘못된 입력");
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
						if (JOptionPane.showConfirmDialog(null, "수정하실겁니까요") == 0) {
							try {
								StuData modifystu = new StuData(view.stuCodeTf.getText(),
										view.stuNameTf.getText(),
										view.stuJuminNum_1Tf.getText(),
										view.stuJuminNum_2Tf.getText(),
										view.stuAddrTf.getText(),
										view.stuCellphoneTf.getText(),
										view.stuPhoneTf.getText(),
										view.stuEntYearTf.getText(),
										view.stuHighSchoolTf.getText(),
										view.stuGradTf.getText(),
										view.stuDepCodeTf.getText(),
										view.stuProCodeTf.getText());
								view.db.modify(modifystu);
								showTable();
								clearField();
								view.stuCodeTf.setEditable(true);
								view.modify.setBackground(Colors.getBtn());   
								view.modify.setForeground(Colors.getBtnTxt());
								view.tableSelected = false;
								view.isModifying = false;
							} catch (Exception ee) {
								System.out.println("오류같은걸 끼얹나?");
							}
						}
					}
				} else {
					if (view.tableSelected) {
						view.isModifying = true;
						view.stuCodeTf.setText(view.table.getValueAt(
								view.index, 0).toString());
						view.modify.setBackground(Color.LIGHT_GRAY);
						view.modify.setForeground(Color.black);
						view.stuCodeTf.setEditable(false);
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
				String[] jumin = view.table.getValueAt(view.index, 3)
						.toString().split("-");
				view.stuCodeTf.setText(view.table.getValueAt(view.index, 0)
						.toString());
				view.stuNameTf.setText(view.table.getValueAt(view.index, 1)
						.toString());
				view.stuAddrTf.setText(view.table.getValueAt(view.index, 2)
						.toString());
				view.stuJuminNum_1Tf.setText(jumin[0]);
				view.stuJuminNum_2Tf.setText(jumin[1]);
				view.stuCellphoneTf.setText(view.table
						.getValueAt(view.index, 4).toString());
				view.stuPhoneTf.setText(view.table.getValueAt(view.index, 5)
						.toString());
				view.stuEntYearTf.setText(view.table.getValueAt(view.index, 6)
						.toString());
				view.stuHighSchoolTf.setText(view.table.getValueAt(view.index, 7)
						.toString());
				view.stuGradTf.setText(view.table.getValueAt(view.index, 8)
						.toString());
				view.stuDepCodeTf.setText(view.table.getValueAt(view.index, 9)
						.toString());
				view.stuProCodeTf.setText(view.table.getValueAt(view.index, 10)
						.toString());
			}
		});

		view.contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!arg0.getSource().equals(view.table)) {
					view.stuCodeTf.setEditable(true);
					view.modify.setBackground(Colors.getBtn());   
					view.modify.setForeground(Colors.getBtnTxt());
					view.tableSelected = false;
					view.isModifying = false;
				}
			}
		});
	}

	public boolean nullCheck() {
		for (JTextField chk : check) {
			if (chk.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				chk.requestFocus();
				return false;
			}
		}
		return true;
	}

	private void showTable() throws Exception {
		ArrayList<StuData> stu = view.db.selectAll();
		view.dtm.setRowCount(0);
		for (int i = 0; i < stu.size(); i++) {
			view.dtm.addRow(stu.get(i).toArray());
		}
	}

	public void clearField() {
		for (JTextField chk : check) {
			chk.setText("");
		}
	}
}
