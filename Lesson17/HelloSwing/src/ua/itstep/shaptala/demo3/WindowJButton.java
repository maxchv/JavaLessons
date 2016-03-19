package ua.itstep.shaptala.demo3;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowJButton extends JFrame {
	
	public WindowJButton(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
		
		Container container = getContentPane();
		
		JButton button = new JButton("Кнопка");
		
		container.add(button);
	}
}
