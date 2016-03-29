package ua.itstep.shaptala.demo1;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class Application {

	private JFrame frame;
	private JTextField textURL;
	private URL url;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
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
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);

		JLabel lblLink = new JLabel("Link");

		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					url = new URL(textURL.getText());
					textURL.setText("");
					startDownload();
				} catch (MalformedURLException ex) {

				} finally {
					btnDownload.setEnabled(false);
				}
			}
		});
		btnDownload.setEnabled(false);

		textURL = new JTextField();
		textURL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					URL url = new URL(textURL.getText());
					btnDownload.setEnabled(true);
				} catch (MalformedURLException ex) {
					btnDownload.setEnabled(false);
				}
			}
		});

		textURL.setColumns(10);

		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414,
										Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblLink).addGap(18)
										.addComponent(textURL, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
										.addGap(18).addComponent(btnDownload)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblLink)
								.addComponent(btnDownload).addComponent(textURL, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addContainerGap()));
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		JPanel downloadPanel = new JPanel();
		scrollPane.setViewportView(downloadPanel);
		downloadPanel.setLayout(new BoxLayout(downloadPanel, BoxLayout.X_AXIS));
		panel.setLayout(gl_panel);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mnFileNew = new JMenuItem("New");
		mnFile.add(mnFileNew);

		JMenuItem mnFileOpen = new JMenuItem("Open");
		mnFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dlg = new JFileChooser();
				dlg.showOpenDialog(frame);
			}
		});
		mnFile.add(mnFileOpen);
	}

	protected void startDownload() {
		if (url != null) {
			SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {

				@Override
				protected Integer doInBackground() {
					try {
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						
						String length = conn.getHeaderField("Content-Length");
						for(Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
							System.out.print("header: " + header.getKey() + " [");
							for(String value: header.getValue()) {
								System.out.print(value+", ");
							}
							System.out.println("]");
						}
						System.out.println("Contetn-Length: " + conn.getContentLengthLong());
						
					} catch (IOException e) {						
						e.printStackTrace();
					}
					return 0;
				}

				@Override
				protected void done() {

				}

				@Override
				protected void process(List<Void> chunks) {

				}

			};
			worker.execute();
		}
	}
}
