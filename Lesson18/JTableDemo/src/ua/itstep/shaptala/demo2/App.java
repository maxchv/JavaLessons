package ua.itstep.shaptala.demo2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


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
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
				
		table = new JTable(new AbstractTableModel(){

			@Override
			public int getRowCount() {				
				return 10;
			}

			@Override
			public int getColumnCount() {
				return 10;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return (rowIndex + 1) * (columnIndex + 1);
			}
			
			@Override
			public String getColumnName(int column) {
				String colName = "";
				if(column < 5)
				{
					colName = "Column " + (column + 1);
				}
				else
				{
					colName = "Other";
				}
				return colName;
			}
			
		});
		scrollPane.setViewportView(table);
	}

}
