package ua.itstep.shaptala.demo3;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import ua.itstep.shaptala.demo3.App.HumanModel;


public class App {
	public class HumanModel extends AbstractTableModel {

		int rows = 0;
		int cols = 0;
		
		String[] columns = {"Имя", "Хвамилия", "Нофелет"};
		private ArrayList<Human> people;
		
		public HumanModel(ArrayList<Human> people) {
			this.people = people;
			rows = people.size();
			cols = 3;
		}

		@Override
		public int getRowCount() {			
			return rows;
		}

		@Override
		public int getColumnCount() {
			return cols;
		}
		
		@Override
		public String getColumnName(int column) {
						
			return columns[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {			
			String value = "";
			switch (columnIndex) {
			case 0:
				value = people.get(rowIndex).getName();
				break;
			case 1:
				value = people.get(rowIndex).getSurname();
				break;
			case 2:
				value = people.get(rowIndex).getTelephone();
				break;
			default:				
				break;
			}
			return value;
		}

	}

	class Human {
	    String name;
	    String surname;
	    String telephone;
	    public Human(String name, String surname, String telephone) {
	        this.name = name;
	        this.surname = surname;
	        this.telephone = telephone;
	    }
	    public String getName() {
	        return name;
	    }
	    public String getSurname() {
	        return surname;
	    }
	    public String getTelephone() {
	        return telephone;
	    }
	}
	
	
	
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
		
		ArrayList<Human> people = new ArrayList<>();
		
		people.add(new Human("Вася", "Пупки", "-123456"));
		people.add(new Human("Юля", "Андрюха", "+3 8 667 77 87"));
		people.add(new Human("Леся", "Саша", "+3 8 912 03 91"));
			
		table = new JTable(new HumanModel(people));
		scrollPane.setViewportView(table);
	}

}
