package ua.itstep.shaptala.demo5;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowBorderLayout extends JFrame {
	
	
	public WindowBorderLayout(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 150);
		
		// ������� ��� ���������� ��������� ����������
		Container panel = getContentPane();
				
		panel.add(new JButton("������"), BorderLayout.NORTH);
		panel.add(new JButton("+"), BorderLayout.EAST);
		panel.add(new JButton("-"), BorderLayout.WEST);
		panel.add(new JButton("������ � ������� ��������"), BorderLayout.SOUTH);
		panel.add(new JButton("� ������"));
		
	}
}
