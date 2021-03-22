package department.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import department.vo.DepData;

public class DepartmentAction {
	DepartmentMain main;
	DepartmentView view;

	public DepartmentAction(DepartmentMain main) {
		this.main = main;
	}

	public void setListeners(DepartmentView view) {
		this.view = view;
		view.select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					ArrayList<DepData> dep = view.db.select(
							view.searchCondition.getSelectedItem().toString(),
							view.searchBox.getText());
					view.dtm.setRowCount(0);
					for (int i = 0; i < dep.size(); i++) {
						view.dtm.addRow(dep.get(i).toArray());
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
						view.db.insert(view.depCodeTf.getText(),
								view.depNameTf.getText(),
								view.depMajorTf.getText());
						showTable();
						clearField();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "등록 실패");
						view.depCodeTf.requestFocus();
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
						if (JOptionPane.showConfirmDialog(null, "수정하실겁니까") == 0) {
							try {
								view.db.modify(view.depNameTf.getText(),
										view.depMajorTf.getText(), view.table
												.getValueAt(view.index, 0)
												.toString());
								showTable();
								clearField();
								view.depCodeTf.setEditable(true);
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
						view.depCodeTf.setText(view.table.getValueAt(
								view.index, 0).toString());
						view.modify.setBackground(Color.LIGHT_GRAY);
						view.modify.setForeground(Color.black);
						view.depCodeTf.setEditable(false);
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
				view.depCodeTf.setText(view.table.getValueAt(view.index, 0)
						.toString());
				view.depNameTf.setText(view.table.getValueAt(view.index, 1)
						.toString());
				view.depMajorTf.setText(view.table.getValueAt(view.index, 2)
						.toString());
			}
		});

		view.contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!arg0.getSource().equals(view.table)) {
					view.depCodeTf.setEditable(true);
					view.modify.setBackground(Color.yellow);
					view.modify.setForeground(Color.gray);
					view.tableSelected = false;
					view.isModifying = false;
				}
			}
		});
	}

	public boolean nullCheck() {
		if (view.depCodeTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			view.depCodeTf.requestFocus();
			return false;
		} else if (view.depNameTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			view.depNameTf.requestFocus();
			return false;
		} else if (view.depMajorTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			view.depMajorTf.requestFocus();
			return false;
		} else
			return true;
	}

	private void showTable() throws Exception {
		ArrayList<DepData> dep = view.db.selectAll();
		view.dtm.setRowCount(0);
		for (int i = 0; i < dep.size(); i++) {
			view.dtm.addRow(dep.get(i).toArray());
		}
	}

	public void clearField() {
		view.depNameTf.setText("");
		view.depMajorTf.setText("");
		view.searchBox.setText("");
		view.depCodeTf.setText("");
	}
}
