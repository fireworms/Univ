package univ.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import univ.dto.CourseData;
import univ.dto.EnrolmentData;
import univ.ui.EnrolmentMain;

public class EnrolmentAction {
	EnrolmentMain main;
	EnrolmentView view;

	public EnrolmentAction(EnrolmentMain main, EnrolmentView view) {
		this.main = main;
		this.view = view;
		setEnrolmentTable(view.userId);
	}

	private void setEnrolmentTable(String userId) {
		try {
			ArrayList<CourseData> course = view.db.enrolSelectAll(userId);
			for (int i = 0; i < course.size(); i++) {
				view.enrolDtm.addRow(course.get(i).toEnrolArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setListeners() {
		view.select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] conditionStr = { "교과코드", "교과명", "개설학과", "수업시수",
						"담당교수", "개설학점" };
				String[] targetStr = { "A.code", "A.subject", "A.department",
						"A.hours", "B.name", "A.score" };
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
					e.printStackTrace();
				}
			}
		});

		view.selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
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
			}
		});

		view.enrolTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				view.enrolTableSelected = true;
				view.enrolIndex = view.enrolTable.getSelectedRow();
			}
		});

		view.insert.addActionListener(new ActionListener() {

			@Override
			// 중복처리
			public void actionPerformed(ActionEvent e) {
				if(view.tableSelected){
					Object[] row = new Object[view.dtm.getColumnCount()];
					for (int i = 0; i < view.enrolDtm.getRowCount(); i++) {
						if (view.enrolDtm.getValueAt(i, 0).equals(
								view.dtm.getValueAt(view.index, 0))) {
							JOptionPane.showMessageDialog(null, "중복");
							return;
						}
					}
					for (int i = 0; i < row.length; i++) {
						row[i] = view.dtm.getValueAt(view.index, i);
					}
					view.enrolDtm.addRow(row);
					view.tableSelected = false;
				}else{
					JOptionPane.showMessageDialog(null, "골라");
				}
			}
		});

		view.remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.enrolTableSelected){
					view.enrolDtm.removeRow(view.enrolIndex);
					view.enrolTableSelected = false;
				}else{
					JOptionPane.showMessageDialog(null, "골라");
				}
			}
		});

		view.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EnrolmentData enrolmentData = new EnrolmentData();
				enrolmentData.setScode(view.userId);
				ArrayList<String> ccodes = new ArrayList<String>();
				int isSuccess = 0;
				for (int i = 0; i < view.enrolDtm.getRowCount(); i++) {
					ccodes.add((String) view.enrolDtm.getValueAt(i, 0));
				}
				enrolmentData.setCcodes(ccodes);
				try {
					isSuccess = view.db.confirm(enrolmentData);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				if (isSuccess == ccodes.size()) {
					JOptionPane.showMessageDialog(null, "성공");
				}

			}
		});
	}

	private void showTable() throws Exception {
		ArrayList<CourseData> course = view.db.selectAll();
		view.dtm.setRowCount(0);
		for (int i = 0; i < course.size(); i++) {
			view.dtm.addRow(course.get(i).toEnrolArray());
		}
	}
}
