package univ.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import univ.ui.CourseMain;
import univ.ui.DepartmentMain;
import univ.ui.EnrolmentMain;
import univ.ui.LoginForm;
import univ.ui.ProfessorMain;
import univ.ui.StudentMain;
import univ.ui.UnivMainMenu;

public class UnivAction {

	ArrayList<JMenuItem> menuItem0;
	ArrayList<JMenuItem> menuItem1;
	ArrayList<JMenuItem> menuItem2;
	ArrayList<JMenuItem> menuItem3;
	ArrayList<JMenuItem> menuItem4;
	ArrayList<JMenuItem> menuItem5;
	String userId;

	public UnivAction(UnivMainMenu view) {
		this.menuItem0 = view.menuItem0;
		this.menuItem1 = view.menuItem1;
		this.menuItem2 = view.menuItem2;
		this.menuItem3 = view.menuItem3;
		this.menuItem4 = view.menuItem4;
		this.menuItem5 = view.menuItem5;
		this.userId = view.userId;
		
		menuItem0.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem0.get(0))){
					StudentMain stu = new StudentMain();
					stu.setVisible(true);
				}
			}
		});
		
		menuItem1.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem1.get(0))){
					DepartmentMain dep = new DepartmentMain();
					dep.setVisible(true);
				}
			}
		});
		
		menuItem2.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem2.get(0))){
					ProfessorMain pro = new ProfessorMain();
					pro.setVisible(true);
				}
			}
		});
		
		menuItem3.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem3.get(0))){
					CourseMain course = new CourseMain();
					course.setVisible(true);
				}
			}
		});
		
		menuItem4.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem4.get(0))){
					EnrolmentMain enrolment = new EnrolmentMain(userId);
					enrolment.setVisible(true);
				}
			}
		});
		
		menuItem5.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(menuItem5.get(0))){
					view.dispose();
					new LoginForm();
				}
			}
		});
	}
}
