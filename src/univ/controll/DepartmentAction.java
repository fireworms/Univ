package univ.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import univ.dao.DepDAO;
import univ.dto.DepData;
import univ.ui.DepartmentMain;

public class DepartmentAction {

	DepartmentMain view;
	JScrollPane pn5;
	JTextField depCodeTf, depNameTf, depMajorTf, searchBox;
	JComboBox<String> searchCondition;
	JButton select, selectAll, insert, modify, remove, exit;
	DefaultTableModel dtm;
	JTable table;
	int index = 0;
	DepDAO db;
	boolean tableSelected;
	boolean isModifying;

	public DepartmentAction(DepartmentMain view) {
		this.view = view;
		this.pn5 = view.pn5;
		this.depCodeTf = view.depCodeTf;
		this.depNameTf = view.depNameTf;
		this.depMajorTf = view.depMajorTf;
		this.searchBox = view.searchBox;
		this.searchCondition = view.searchCondition;
		this.select = view.select;
		this.selectAll = view.selectAll;
		this.insert = view.insert;
		this.modify = view.modify;
		this.remove = view.remove;
		this.exit = view.exit;
		this.dtm = view.dtm;
		this.table = view.table;
		this.db = new DepDAO("major");
		this.tableSelected = false;
		this.isModifying = false;
		setListeners();
	}

	public void setListeners() {

		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					ArrayList<DepData> dep = db.select(searchCondition
							.getSelectedItem().toString(), searchBox.getText());
					dtm.setRowCount(0);
					for (int i = 0; i < dep.size(); i++) {
						dtm.addRow(dep.get(i).toArray());
					}
				} catch (Exception e) {
					System.out.println("오류같은걸 끼얹나?");
				}
			}
		});

		selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					showTable();
				} catch (Exception e) {
					System.out.println("오류같은걸 끼얹나?");
				}
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (nullCheck()) {
					try {
						db.insert(depCodeTf.getText(), depNameTf.getText(),
								depMajorTf.getText());
						showTable();
						clearField();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "등록 실패");
						depCodeTf.requestFocus();
					}
				}
			}
		});

		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableSelected) {
					try {
						if (tableSelected) {
							db.remove(table.getValueAt(index, 0).toString());
							showTable();
							tableSelected = false;
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
							index = Integer.parseInt(indexString) - 1;
							String name = dtm.getValueAt(index, 0).toString();
							db.remove(name);
							dtm.removeRow(index);
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, "잘못된 입력");
						}
					}
				}
			}
		});

		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isModifying) {
					if (nullCheck()) {
						if (JOptionPane.showConfirmDialog(null, "수정하실겁니까") == 0) {
							try {
								db.modify(depNameTf.getText(),
										depMajorTf.getText(),
										table.getValueAt(index, 0).toString());
								showTable();
								clearField();
								depCodeTf.setEditable(true);
								modify.setBackground(Color.yellow);
								modify.setForeground(Color.gray);
								tableSelected = false;
								isModifying = false;
							} catch (Exception ee) {
								System.out.println("오류같은걸 끼얹나?");
							}
						}
					}
				} else {
					if (tableSelected) {
						isModifying = true;
						depCodeTf
								.setText(table.getValueAt(index, 0).toString());
						modify.setBackground(Color.LIGHT_GRAY);
						modify.setForeground(Color.black);
						depCodeTf.setEditable(false);
					}
				}
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				tableSelected = true;
				index = table.getSelectedRow();
				depCodeTf.setText(table.getValueAt(index, 0).toString());
				depNameTf.setText(table.getValueAt(index, 1).toString());
				depMajorTf.setText(table.getValueAt(index, 2).toString());
			}
		});

		view.contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!arg0.getSource().equals(table)) {
					depCodeTf.setEditable(true);
					modify.setBackground(Color.yellow);
					modify.setForeground(Color.gray);
					tableSelected = false;
					isModifying = false;
				}
			}
		});
	}

	public boolean nullCheck() {
		if (depCodeTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			depCodeTf.requestFocus();
			return false;
		} else if (depNameTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			depNameTf.requestFocus();
			return false;
		} else if (depMajorTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
			depMajorTf.requestFocus();
			return false;
		} else
			return true;
	}

	private void showTable() throws Exception {
		ArrayList<DepData> dep = db.selectAll();
		dtm.setRowCount(0);
		for (int i = 0; i < dep.size(); i++) {
			dtm.addRow(dep.get(i).toArray());
		}
	}

	public void clearField() {
		depNameTf.setText("");
		depMajorTf.setText("");
		searchBox.setText("");
		depCodeTf.setText("");
	}
}
