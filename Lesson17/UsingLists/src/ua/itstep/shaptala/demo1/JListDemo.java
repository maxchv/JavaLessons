package ua.itstep.shaptala.demo1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListDemo extends JFrame implements ListSelectionListener {

	JLabel output;

	String[] data = { "Chrome", "Firefox", "Internet Explorer", "Safari", "Opera", "Morrowind", "Oblivion", "NFS",
			"Half Life 2", "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2", "Hitman", "Morrowind", "Oblivion",
			"NFS", "Half Life 2", "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2", "Hitman", "Morrowind",
			"Oblivion", "NFS", "Half Life 2", "Hitman", "IL-2", "CMR", "NFS Undercover", "Star Wars", "Call of Duty",
			"IL-2", "CMR", "NFS Undercover", "Star Wars", "Call of Duty", "IL-2", "CMR", "NFS Undercover", "Star Wars",
			"Call of Duty", "IL-2", "CMR", "NFS Undercover", "Star Wars", "Call of Duty", "IL-2", "CMR",
			"NFS Undercover", "Star Wars", "Call of Duty", "IL-2", "CMR", "NFS Undercover", "Star Wars", "Call of Duty",
			"Arena", "Dagerfall", "MS Office", "Open Office", "Windows", "Arena", "Dagerfall", "MS Office",
			"Open Office", "Windows", "Arena", "Dagerfall", "MS Office", "Open Office", "Windows", "Arena", "Dagerfall",
			"MS Office", "Open Office", "Windows", "Mac OS", "Ubuntu" };

	public JListDemo(String title) {
		super(title);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(createContentPane(), BorderLayout.CENTER);
		setPreferredSize(new Dimension(330, 450));
		pack();

		setLocationRelativeTo(null);
	}

	private Container createContentPane() {
		Box box = Box.createVerticalBox();

		box.add(new JLabel("VERTICAL and SINGLE_SELECTION"));
		box.add(createList(JList.VERTICAL, ListSelectionModel.SINGLE_SELECTION));
		box.add(new JLabel("VERTICAL_WRAP and SINGLE_INTERVAL_SELECTION"));
		box.add(createList(JList.VERTICAL_WRAP, ListSelectionModel.SINGLE_INTERVAL_SELECTION));
		box.add(new JLabel("HORIZONTAL_WRAP and MULTIPLE_INTERVAL_SELECTION"));
		box.add(createList(JList.HORIZONTAL_WRAP, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
		output = new JLabel("output");
		box.add(output);
		return box;
	}

	private Component createList(int layoutOrientation, int selectionMode) {

		JList<String> list = new JList<>(data);

		list.setSelectionMode(selectionMode);

		list.setLayoutOrientation(layoutOrientation);

		list.addListSelectionListener(this);

		return new JScrollPane(list);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JListDemo app = new JListDemo("JList demo");
				app.setVisible(true);
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> list = (JList<?>) e.getSource();

		output.setText(Arrays.toString(list.getSelectedValuesList().toArray()));
	}

}
