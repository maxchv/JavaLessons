package ua.itstep.shaptala.demo2;

import javax.swing.JFrame;

public class SimpleWindow extends JFrame {
	public SimpleWindow(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
	}
}
