package univ.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import univ.controll.UnivAction;

public class UnivMainMenu extends JFrame {

	public JMenuBar menuBar = new JMenuBar();
	public ArrayList<JMenu> menu = new ArrayList<JMenu>();
	public ArrayList<JMenuItem> menuItem1 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem2 = new ArrayList<JMenuItem>();
	public ArrayList<JMenuItem> menuItem3 = new ArrayList<JMenuItem>();
	public ArrayList<String> menuStr = new ArrayList<String>();
	public ArrayList<String> menuItemStr1 = new ArrayList<String>();
	public ArrayList<String> menuItemStr2 = new ArrayList<String>();
	public ArrayList<String> menuItemStr3 = new ArrayList<String>();

	public UnivMainMenu() {
		this.setSize(800, 800);
		this.setJMenuBar(menuBar);
		menuStr.add("학과관리");
		menuStr.add("교수관리");
		menuStr.add("교과목관리");
		menuItemStr1.add("등록");
		menuItemStr2.add("등록");
		menuItemStr3.add("등록");

		for (int i = 0; i < menuStr.size(); i++) {
			menu.add(new JMenu(menuStr.get(i)));
			menuBar.add(menu.get(i));
		}
		for (int i = 0; i < menuItemStr1.size(); i++) {
			menuItem1.add(new JMenuItem(menuItemStr1.get(i)));
			menu.get(0).add(menuItem1.get(i));
		}
		for (int i = 0; i < menuItemStr2.size(); i++) {
			menuItem2.add(new JMenuItem(menuItemStr2.get(i)));
			menu.get(1).add(menuItem2.get(i));
		}
		for (int i = 0; i < menuItemStr3.size(); i++) {
			menuItem3.add(new JMenuItem(menuItemStr3.get(i)));
			menu.get(2).add(menuItem3.get(i));
		}
		
		UnivAction aa = new UnivAction(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		UnivMainMenu aa = new UnivMainMenu();
		aa.setVisible(true);

	}

}
