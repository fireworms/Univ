package univ.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import univ.controll.UnivAction;
import univ.dto.LoginData;

public class UnivMainMenu extends JFrame {

	private static final long serialVersionUID = 1001378656795064662L;
	
	public JMenuBar menuBar = new JMenuBar();
	public ArrayList<JMenu> menu = new ArrayList<JMenu>();
	public ArrayList<JMenuItem> menuItem0 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem1 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem2 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem3 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem4 = new ArrayList<JMenuItem>();
	public ArrayList<String> menuStr = new ArrayList<String>();
	public ArrayList<String> menuItemStr0 = new ArrayList<String>();
	public ArrayList<String> menuItemStr1 = new ArrayList<String>();
	public ArrayList<String> menuItemStr2 = new ArrayList<String>();
	public ArrayList<String> menuItemStr3 = new ArrayList<String>();
	public ArrayList<String> menuItemStr4 = new ArrayList<String>();
	public String userId;
	public String userName;

	public UnivMainMenu(LoginData getLoginData) {
		this.setSize(800, 800);
		userId = getLoginData.getCode();
		userName = getLoginData.getName();
		String title = "학사관리 프로그램  사용자 번호 : " + userId + " 이름 : " + userName; 
		this.setTitle(title);
		this.setJMenuBar(menuBar);
		menuStr.add("학생관리");
		menuStr.add("학과관리");
		menuStr.add("교수관리");
		menuStr.add("교과목관리");
		menuStr.add("수강");
		menuItemStr0.add("등록");
		menuItemStr1.add("등록");
		menuItemStr2.add("등록");
		menuItemStr3.add("등록");
		menuItemStr4.add("등록");

		for (int i = 0; i < menuStr.size(); i++) {
			menu.add(new JMenu(menuStr.get(i)));
			menuBar.add(menu.get(i));
		}
		
		for (int i = 0; i < menuItemStr0.size(); i++) {
			menuItem0.add(new JMenuItem(menuItemStr0.get(i)));
			menu.get(0).add(menuItem0.get(i));
		}
		
		for (int i = 0; i < menuItemStr1.size(); i++) {
			menuItem1.add(new JMenuItem(menuItemStr1.get(i)));
			menu.get(1).add(menuItem1.get(i));
		}
		
		for (int i = 0; i < menuItemStr2.size(); i++) {
			menuItem2.add(new JMenuItem(menuItemStr2.get(i)));
			menu.get(2).add(menuItem2.get(i));
		}
		for (int i = 0; i < menuItemStr3.size(); i++) {
			menuItem3.add(new JMenuItem(menuItemStr3.get(i)));
			menu.get(3).add(menuItem3.get(i));
		}
		for (int i = 0; i < menuItemStr4.size(); i++) {
			menuItem4.add(new JMenuItem(menuItemStr4.get(i)));
			menu.get(4).add(menuItem4.get(i));
		}
		
		if(!(getLoginData.getCode().charAt(0)=='p' || getLoginData.getCode().equals("admin"))){
			menu.get(2).setVisible(false);
			menu.get(3).setVisible(false);
		}
		
		new UnivAction(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}
