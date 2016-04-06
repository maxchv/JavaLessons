package ua.itstep.shaptala.demo1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

public class App {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Object[][] data = {
			    {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
			};
		
		String[] columnNames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
		
		table = new JTable(data, columnNames);
		frame.getContentPane().add(table, BorderLayout.CENTER);		
		
	}

}
