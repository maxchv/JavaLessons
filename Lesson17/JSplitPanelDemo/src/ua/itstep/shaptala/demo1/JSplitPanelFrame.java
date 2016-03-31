package ua.itstep.shaptala.demo1;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import sun.misc.Perf.GetPerfAction;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;

public class JSplitPanelFrame {

	private JFrame frame;
	private DefaultListModel<Path> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSplitPanelFrame window = new JSplitPanelFrame();
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
	public JSplitPanelFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		model = new DefaultListModel<>();
		
		JLabel label = new JLabel("");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setRightComponent(label);
		
		JList<Path> list = new JList<>(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Path selected = list.getSelectedValue();
				if(selected.toString().endsWith(".gif")) {
					label.setIcon(new ImageIcon(selected.toString()));
				}
			}
		});
		list.setCellRenderer(new DefaultListCellRenderer() {
			private Image getScaledImage(Image srcImg, int w, int h){
		        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		        Graphics2D g2 = resizedImg.createGraphics();
		        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		        g2.drawImage(srcImg, 0, 0, w, h, null);
		        g2.dispose();
		        return resizedImg;
		    }
			
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				JLabel lbl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				//lbl.setIcon(new ImageIcon(getScaledImage(new ImageIcon(value.toString()).getImage(), 32, 32)));				
				lbl.setHorizontalTextPosition(JLabel.RIGHT);
				return lbl;
			}
			
		});
		list.setSize(new Dimension(150, 250));
		list.setBorder(new EmptyBorder(5, 5, 5, 5));
		splitPane.setLeftComponent(new JScrollPane(list));
		
		JLabel out = new JLabel();
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Path path = list.getSelectedValue();
				Icon img = new ImageIcon(path.toString());
				out.setSize(img.getIconWidth(), img.getIconHeight());
				out.setIcon(img);
			}
		});
		splitPane.setDividerLocation(150);
		splitPane.setRightComponent(out);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenDirectory = new JMenuItem("Open directory");
		mntmOpenDirectory.addActionListener(new ActionListener() {
			private File dir;

			public void actionPerformed(ActionEvent e) {
				JFileChooser dlg = new JFileChooser();
				dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(dlg.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					dir = dlg.getSelectedFile();
					try {
						model.clear();
						DirectoryStream<Path> all = Files.newDirectoryStream(dir.toPath());
						for(Path path : all) {
							if(path.toFile().isFile()) {
								model.addElement(path);
							}
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmOpenDirectory);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
	}

}
