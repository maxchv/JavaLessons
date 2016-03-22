package ua.itstep.shaptala.demo8;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowSetLocation extends JFrame {
	
	
	public WindowSetLocation(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();		
		panel.setLayout(null);
		
		JButton btn = new JButton(" нопка");
		
		btn.setBorder(BorderFactory.createTitledBorder("test"));
		//btn.setSize(80, 20);
		btn.setSize(btn.getPreferredSize());
		
		btn.setLocation(20, 20);
		panel.add(btn);		
						
		btn = new JButton(" нопка с длинной надписью");
		//btn.setSize(120, 40);
		btn.setSize(btn.getPreferredSize());
		btn.setLocation(70, 50);
		panel.add(btn);
				
		setContentPane(panel);
		
		setSize(250, 100);		
	}
}
