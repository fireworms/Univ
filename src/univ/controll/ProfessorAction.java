package univ.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import univ.ui.ProfessorMain;
import univ.vo.ProData;

public class ProfessorAction {
	ProfessorMain main;
	ProfessorView view;
	JTextField[] check;

	public ProfessorAction(ProfessorMain main, ProfessorView view) {
		this.main = main;
		this.view = view;
		check = new JTextField[11];
		check[0] = view.proCodeTf;
		check[1] = view.proNameTf;
		check[2] = view.proJuminNum_1Tf;
		check[3] = view.proJuminNum_2Tf;
		check[4] = view.proAddrTf;
		check[5] = view.proCellphoneTf;
		check[6] = view.proPhoneTf;
		check[7] = view.proHireYearTf;
		check[8] = view.proDegreeTf;
		check[9] = view.proMajorTf;
		check[10] = view.proLabTf;
	}

	public void setListeners() {
		view.select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] conditionStr = { "교수코드", "이 름", "주 소", "주민등록번호",
						"휴대폰", "전 화", "임용년도", "학 위", "학과/전공", "연구실" };
				String[] targetStr = { "code", "name", "addr", "juminnum",
						"cellphone", "phone", "hireyear", "degree",
						"department", "labnum" };
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
					ArrayList<ProData> pro = view.db.select(condition,
							searchText);
					view.dtm.setRowCount(0);
					for (int i = 0; i < pro.size(); i++) {
						view.dtm.addRow(pro.get(i).toArray());
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
						ProData addPro = new ProData(view.proCodeTf.getText(),
								view.proNameTf.getText(),
								view.proJuminNum_1Tf.getText(),
								view.proJuminNum_2Tf.getText(),
								view.proAddrTf.getText(),
								view.proCellphoneTf.getText(),
								view.proPhoneTf.getText(),
								view.proHireYearTf.getText(),
								view.proDegreeTf.getText(),
								view.proMajorTf.getText(),
								view.proLabTf.getText());
						view.db.insert(addPro);
						showTable();
						clearField();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "등록 실패");
						view.proCodeTf.requestFocus();
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
								ProData modifyPro = new ProData(view.proCodeTf.getText(),
										view.proNameTf.getText(),
										view.proJuminNum_1Tf.getText(),
										view.proJuminNum_2Tf.getText(),
										view.proAddrTf.getText(),
										view.proCellphoneTf.getText(),
										view.proPhoneTf.getText(),
										view.proHireYearTf.getText(),
										view.proDegreeTf.getText(),
										view.proMajorTf.getText(),
										view.proLabTf.getText());
								view.db.modify(modifyPro);
								showTable();
								clearField();
								view.proCodeTf.setEditable(true);
								view.modify.setBackground(Color.yellow);
								view.modify.setForeground(Color.gray);
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
						view.proCodeTf.setText(view.table.getValueAt(
								view.index, 0).toString());
						view.modify.setBackground(Color.LIGHT_GRAY);
						view.modify.setForeground(Color.black);
						view.proCodeTf.setEditable(false);
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
				view.proCodeTf.setText(view.table.getValueAt(view.index, 0)
						.toString());
				view.proNameTf.setText(view.table.getValueAt(view.index, 1)
						.toString());
				view.proAddrTf.setText(view.table.getValueAt(view.index, 2)
						.toString());
				view.proJuminNum_1Tf.setText(jumin[0]);
				view.proJuminNum_2Tf.setText(jumin[1]);
				view.proCellphoneTf.setText(view.table
						.getValueAt(view.index, 4).toString());
				view.proPhoneTf.setText(view.table.getValueAt(view.index, 5)
						.toString());
				view.proHireYearTf.setText(view.table.getValueAt(view.index, 6)
						.toString());
				view.proDegreeTf.setText(view.table.getValueAt(view.index, 7)
						.toString());
				view.proMajorTf.setText(view.table.getValueAt(view.index, 8)
						.toString());
				view.proLabTf.setText(view.table.getValueAt(view.index, 9)
						.toString());
			}
		});

		view.contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!arg0.getSource().equals(view.table)) {
					view.proCodeTf.setEditable(true);
					view.modify.setBackground(Color.yellow);
					view.modify.setForeground(Color.gray);
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
		ArrayList<ProData> pro = view.db.selectAll();
		view.dtm.setRowCount(0);
		for (int i = 0; i < pro.size(); i++) {
			view.dtm.addRow(pro.get(i).toArray());
		}
	}

	public void clearField() {
		for (JTextField chk : check) {
			chk.setText("");
		}
	}
}
