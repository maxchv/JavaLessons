package ua.itstep.shaptala.demo1;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;

public class Application extends JFrame implements ActionListener {

	JTextArea textArea;
	JButton btn;
	public Application(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JToolBar toolbar = new JToolBar("Toolbar");
		btn = new JButton("Open");
		btn.addActionListener(this);
		toolbar.add(btn);
		
		setJMenuBar(createMainMenu());
		textArea = new JTextArea(20, 60);		
		textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
		getContentPane().add(toolbar, BorderLayout.NORTH);		
		getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		getContentPane().add(new JLabel("Ready"), BorderLayout.SOUTH);
		pack();
	}

	private JMenuBar createMainMenu() {
		JMenuBar menu = new JMenuBar();
		
		menu.add(createFileMenu());
		
		return menu;
	}

	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.addActionListener(this);		
			
		fileMenu.add(creatSubMenu("Open", KeyEvent.VK_O));						
		fileMenu.add(creatSubMenu("New", KeyEvent.VK_N));						
		fileMenu.add(creatSubMenu("Save", KeyEvent.VK_S));		
		fileMenu.addSeparator();
		fileMenu.add(creatSubMenu("Quit", KeyEvent.VK_Q));
		
		return fileMenu;
	}

	private JMenuItem creatSubMenu(String name, int mnemonic) {
		JMenuItem submenu = new JMenuItem(name);
		submenu.setMnemonic(mnemonic);
		submenu.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_MASK));
		submenu.addActionListener(this);
		return submenu;
	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait( new Runnable() {
			public void run() {
				Application app = new Application("Menu and Toolbar");
				app.setVisible(true);
			}
		});
		System.out.println("Main thread");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.append(e.getActionCommand() + "\n");
		textArea.append(e.getSource().getClass().getName() + "\n");
		if(e.getSource() == btn)
		{
			JOptionPane.showMessageDialog(this, "Это была кнопка");
		}
		JFileChooser dlg;
		switch(e.getActionCommand())
		{
		case "New":
			if(!isSaved())
			{
				dlg = new JFileChooser();
				dlg.showSaveDialog(this);
			}
			textArea.setText("");
			break;
		case "Open":
			dlg = new JFileChooser();
			if(dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				textArea.setText(dlg.getSelectedFile().getName());
			}
			break;
		case "Save":
			dlg = new JFileChooser();
			dlg.showSaveDialog(this);
			break;
		case "Quit":
			System.exit(0);
			break;
		}
	}

	private boolean isSaved() {		
		return true;
	}
}
