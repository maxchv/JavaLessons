package ua.itstep.shaptala.demo;

import javax.swing.*;

public class HelloJFrame {
	public static void main(String[] args) {
		JFrame myWindow = new JFrame("Здравствуй GUI");
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setSize(400, 300);
		myWindow.setVisible(true);
		
	}
}
