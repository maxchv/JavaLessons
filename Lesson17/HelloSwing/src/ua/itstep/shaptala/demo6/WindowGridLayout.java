package ua.itstep.shaptala.demo6;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowGridLayout extends JFrame {
	
	
	public WindowGridLayout(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
		
		// Панелья для размещения элементов управления
		Container panel = new JPanel();
		
		panel.setLayout(new GridLayout(2,  3, 5, 10));
				
		panel.add(new JButton("Кнопка"));
		panel.add(new JButton("+"));
		panel.add(new JButton("-"));
		panel.add(new JButton("Кнопка с длинной надписью"));
		panel.add(new JButton("еще кнопка"));
		
		setContentPane(panel);
	}
}
