package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DBest.DepData;
import DBest.UseDB;

public class Ex extends JFrame {

	private static final long serialVersionUID = 1L;

	int width = 620;
	
	Ex() {
		this.setSize(new Dimension(width, 550));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ExView view = new ExView();
		view.setGUIs(this);
		view.setListeners(this);
	}

	public static void main(String[] args) {
		Ex a = new Ex();
		a.setVisible(true);
	}

}