package ua.itstep.shaptala.demo2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class App extends JFrame {

	public App(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setContentPane(createPanel());		
	}

	private Container createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel("<html>x<sup>2</sup></html>", new ImageIcon("D:\\accept1.png"), JLabel.RIGHT);
		label.setLocation(10, 10);
		label.setFont(new Font("Arial", Font.ITALIC, 20));
		//label.setSize(50, 30);
		label.setSize(label.getPreferredSize());
		
		JTextField text = new JTextField(20);
		text.setLocation(100, 10);
		text.setSize(text.getPreferredSize());
		
		JLabel out = new JLabel();
		out.setLocation(10, 200);
		out.setSize(200, 100);
		
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {			
				//super.keyPressed(e);
				out.setText(out.getText() + e.getKeyChar());
			}
		});
		
		JToggleButton toggle = new JToggleButton("toggle");		
		toggle.setLocation(10, 50);
		toggle.setSize(toggle.getPreferredSize());
		
		class Listener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				out.setText(e.getActionCommand());
				
			}
		};
		
		Listener listener = new Listener();
		
		toggle.addActionListener(listener);
		
		JCheckBox checkBox = new JCheckBox("Показывать рекламу?");
		checkBox.setLocation(10, 80);
		checkBox.setSize(checkBox.getPreferredSize());
		checkBox.addActionListener(listener);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton radio1 = new JRadioButton("radio 1");
		radio1.setLocation(10, 110);
		radio1.addActionListener(listener);
		
		radio1.setSize(radio1.getPreferredSize());
		JRadioButton radio2 = new JRadioButton("radio 2");
		radio2.setLocation(10, 140);
		radio2.setSize(radio1.getPreferredSize());
		radio2.addActionListener(listener);
		
		JRadioButton radio3 = new JRadioButton("radio 3");
		radio3.setLocation(10, 170);
		radio3.setSize(radio1.getPreferredSize());
		radio3.addActionListener(listener);
		
		JTextArea textArea = new JTextArea();
		textArea.setLocation(200, 50);
		textArea.setSize(200, 150);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setLocation(200, 50);
		scroll.setSize(200, 150);
		
		panel.add(scroll);
		
		bg.add(radio1);
		bg.add(radio2);
		bg.add(radio3);
		
		panel.add(radio1);
		panel.add(radio2);
		panel.add(radio3);
		panel.add(checkBox);
		panel.add(toggle);
		panel.add(out);		
		panel.add(text);
		panel.add(label);
		
		
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame app = new App("Base components");
				app.setVisible(true);
			}
		});

	}

}
