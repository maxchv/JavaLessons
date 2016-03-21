package ua.itstep.shaptala.demo9;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowCalculator extends JFrame implements ActionListener {
	
	JLabel label;
	public WindowCalculator(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setJMenuBar(createMenu());
		
		setContentPane(createPane());
		
		pack();	
		
	}

	private JMenuBar createMenu() {
		JMenuBar main = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem open = new JMenuItem("Quit");
		open.setMnemonic(KeyEvent.VK_Q);
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		file.add(open);
		main.add(file);
		return main;
	}

	private Box createPane() {
		Box vBox = Box.createVerticalBox();
		
		JPanel panel = new JPanel();
		label = new JLabel("-");
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.EAST);
		
		vBox.add(panel);
		
		JPanel buttonsPanel = new JPanel();
		
		buttonsPanel.setLayout(new GridLayout(4, 3, 5, 5));
		JButton btn;
		for(int i=0; i<10; i++) {
			btn = new JButton(new Integer(i).toString());
			btn.addActionListener(this);
			buttonsPanel.add(btn);	
		}
		buttonsPanel.add(new JButton("+"));
		buttonsPanel.add(new JButton("-"));
		buttonsPanel.add(new JButton("*"));
		buttonsPanel.add(new JButton("/"));
		buttonsPanel.add(new JButton("="));
		buttonsPanel.add(new JButton("CE"));
		
		vBox.add(new JTextField());
		
		vBox.add(buttonsPanel);
		return vBox;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		label.setText(btn.getText());
		
	}

	
}
