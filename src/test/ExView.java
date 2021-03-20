package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DBest.DepData;
import DBest.UseDB;

public class ExView {
	int width = 620;
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
	UseDB db = new UseDB();
	boolean tableSelected = false;
	boolean isModifying = false;
	
	public void setGUIs(Ex view) {
		Container contentPane = view.getContentPane();
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

		String[] colNames = { "�а��ڵ�", "�а���", "������" };
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

		topLbl = new JLabel("�а�/���� ����");
		pn1.add(topLbl);

		depCodeLbl = new JLabel("�а��ڵ� ");
		pn2_1.add(depCodeLbl);
		depCodeTf = new JTextField();
		pn2_1.add(depCodeTf);

		depNameLbl = new JLabel(" �а��� ");
		pn2_1.add(depNameLbl);
		depNameTf = new JTextField();
		pn2_1.add(depNameTf);

		depMajorLbl = new JLabel(" ������ ");
		pn2_1.add(depMajorLbl);
		depMajorTf = new JTextField();
		pn2_1.add(depMajorTf);

		String[] conditions = { "�а��ڵ�", "�а���", "������" };
		searchCondition = new JComboBox<String>(conditions);
		searchCondition.setPreferredSize(new Dimension(100, 20));
		pn4.add(searchCondition);
		pn4.add(emptyLbl1);

		searchBox = new JTextField();
		pn4.add(searchBox);
		pn4.add(emptyLbl2);

		select = new JButton("��ȸ");
		pn4.add(select);
		pn4.add(emptyLbl3);

		selectAll = new JButton("��ü��ȸ");
		pn4.add(selectAll);

		insert = new JButton("���");
		insert.setForeground(Color.white);
		insert.setBackground(Color.blue);
		pn6.add(insert);
		modify = new JButton("����");
		modify.setForeground(Color.gray);
		modify.setBackground(Color.yellow);
		pn6.add(modify);
		remove = new JButton("����");
		remove.setForeground(Color.white);
		remove.setBackground(Color.red);
		pn6.add(remove);
		exit = new JButton("����");
		exit.setForeground(Color.white);
		exit.setBackground(Color.black);
		pn6.add(exit);
	}
	public void setListeners(Ex view) {
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
					System.out.println("���������� ����?");
				}
			}
		});

		selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					showTable();
				} catch (Exception e) {
					System.out.println("���������� ����?");
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
						JOptionPane.showMessageDialog(null, "��� ����");
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
						System.out.println("���������� ����?");
					}
				} else {
					String indexString = JOptionPane.showInputDialog(null,
							"���°�� �����ұ�", "�ٻ���", 0);
					if (indexString != null && indexString != "") {
						try {
							index = Integer.parseInt(indexString) - 1;
							String name = dtm.getValueAt(index, 0).toString();
							db.remove(name);
							dtm.removeRow(index);
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, "�߸��� �Է�");
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
						if (JOptionPane.showConfirmDialog(null, "�����Ͻǰ̴ϱ��") == 0) {
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
								System.out.println("���������� ����?");
							}
						}
					}
				} else {
					if (tableSelected) {
						isModifying = true;
						depCodeTf.setText(table.getValueAt(index, 0).toString());
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
				
				System.exit(0);
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
		
		view.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!arg0.getSource().equals(table)){
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
			JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
			depCodeTf.requestFocus();
			return false;
		} else if (depNameTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
			depNameTf.requestFocus();
			return false;
		} else if (depMajorTf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
			depMajorTf.requestFocus();
			return false;
		} else
			return true;
	}
	
	private void showTable() throws Exception{
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
