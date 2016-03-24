package ua.itstep.shaptala.demo2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class JListModelDemo extends JFrame {

	DefaultListModel<String> model;
	private JButton remove;
	private JList<String> list;
	private JTextField text;
	
	public JListModelDemo(String title) {
		super(title);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(createContentPane(), BorderLayout.CENTER);
		getContentPane().add(createEditBox(), BorderLayout.SOUTH);
		//setPreferredSize(new Dimension(400, 450));
		pack();
		setLocationRelativeTo(null);
		text.requestFocus();
	}

	private Component createEditBox() {
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 10, 5);
		JPanel panel = new JPanel(flow);
		
		panel.add(new JLabel("New item"));
		text = new JTextField(10); 
		
		panel.add(text);
		
		JButton add = new JButton("Add");
		getRootPane().setDefaultButton(add);
		add.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!text.getText().isEmpty()) {
					model.addElement(text.getText());
				}
			}
		});
		panel.add(add);
		
		remove = new JButton("Remove");
		remove.setEnabled(false);
		remove.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!list.isSelectionEmpty()) {
					int[] idx = list.getSelectedIndices();
					for(int i=idx.length-1; i>=0; i--) {
						model.remove(idx[i]);
					}
				}
			}
		});
		panel.add(remove);
		
		return panel;
	}

	private Component createContentPane() {		
		return createList(JList.VERTICAL, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	private Component createList(int layoutOrientation, int selectionMode) {
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setSelectionMode(selectionMode);
		list.setLayoutOrientation(layoutOrientation);
		
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(list.isSelectionEmpty()) {
					remove.setEnabled(false);
				} else {
					remove.setEnabled(true);
				}
			}
		});
		
		return new JScrollPane(list);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame app = new JListModelDemo("JList demo");
				app.setVisible(true);
			}
		});
	}

}
