package ua.itstep.shaptala.demo7;

import java.awt.Container;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowVerticalBox extends JFrame {
	
	
	public WindowVerticalBox(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
		
		Container box = Box.createVerticalBox();
						
		box.add(new JButton(" нопка"));
		box.add(Box.createVerticalStrut(10));
		box.add(new JButton("+"));
		box.add(Box.createVerticalStrut(10));
		JButton rightButton = new JButton("-");
		//rightButton.setAlignmentX(JColorChooser.RIGHT_ALIGNMENT);
		box.add(rightButton);
		box.add(Box.createVerticalStrut(10));
		box.add(new JButton(" нопка с длинной надписью"));
		box.add(Box.createVerticalStrut(10));
				
		setContentPane(box);
	}
}
