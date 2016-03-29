package ua.itstep.shaptala.demo1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class Application {

	private JFrame frmDownloader;
	private JTextField textURL;
	private URL url;
	private JButton btnOpen;
	private JButton btnDownload;
	private JProgressBar progressBar;
	private JLabel lblStatus;
	private JPanel panel_1;
	private JLabel lblBytes;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmDownloader.setVisible(true);
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
		frmDownloader = new JFrame();
		frmDownloader.setTitle("Downloader");
		frmDownloader.setBounds(100, 100, 470, 168);
		frmDownloader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmDownloader.getContentPane().add(panel, BorderLayout.WEST);

		JLabel lblLink = new JLabel("Link");

		btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					url = new URL(textURL.getText());
					textURL.setEditable(false);
					startDownload();
				} catch (MalformedURLException ex) {

				} finally {
					btnDownload.setEnabled(false);
					btnOpen.setEnabled(false);
				}
			}
		});

		textURL = new JTextField();
		lblLink.setLabelFor(textURL);
		textURL.setColumns(10);

		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = lblStatus.getText();
				if(!status.isEmpty()) {
					String baseName = status.substring(status.lastIndexOf(":")+2);
					File f = new File(baseName);
					if(f.exists()) {
						try {
							Desktop.getDesktop().open(f);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnOpen.setEnabled(false);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblLink).addGap(18)
								.addComponent(textURL, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOpen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnDownload, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblLink)
								.addComponent(btnDownload).addComponent(textURL, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(btnOpen))
						.addGap(41)));
		panel.setLayout(gl_panel);

		JMenuBar menuBar = new JMenuBar();
		frmDownloader.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mnFileNew = new JMenuItem("New");
		mnFile.add(mnFileNew);

		JMenuItem mnFileOpen = new JMenuItem("Open");
		mnFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dlg = new JFileChooser();
				dlg.showOpenDialog(frmDownloader);
			}
		});
		mnFile.add(mnFileOpen);

		panel_1 = new JPanel();
		frmDownloader.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		lblStatus = new JLabel("Ready");
		panel_1.add(lblStatus);

		separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.light"));
		panel_1.add(separator);

		lblBytes = new JLabel("0 bytes");
		lblBytes.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(lblBytes);
	}

	protected void startDownload() {

		if (url != null) {
			SwingWorker<Integer, Integer> worker = new SwingWorker<Integer, Integer>() {
				Long contentLength = null;
				String baseName;

				@Override
				protected Integer doInBackground() {
					int readed_bytes = 0;
					try {
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						contentLength = conn.getContentLengthLong();
						conn.connect();

						progressBar.setMaximum(contentLength.intValue());
						// Get file name
						String fullPath = conn.getURL().getPath();
						baseName = fullPath.substring(fullPath.lastIndexOf("/") + 1);
						lblStatus.setText("File: " + baseName);

						// printHeader(conn);
						InputStream is = conn.getInputStream();

						final int buff_length = 8;
						byte[] buff = new byte[buff_length];
						int len = 0;
						OutputStream os = new FileOutputStream(baseName);
						{
							while (readed_bytes < contentLength) {
								len = is.read(buff);
								os.write(buff, 0, len);
								readed_bytes += buff_length;
								publish(readed_bytes);
							}
						}
						os.close();
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return readed_bytes;
				}

				private void printHeader(HttpURLConnection conn) {
					for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
						System.out.print(header + ": ");
						header.getValue().forEach((t) -> {
							System.out.print(t + ", ");
						});
						System.out.println();
					}
				}

				@Override
				protected void done() {
					textURL.setEditable(true);
					btnDownload.setEnabled(true);
					btnOpen.setEnabled(true);
				}

				@Override
				protected void process(List<Integer> chunks) {
					int last = chunks.get(chunks.size() - 1);
					lblBytes.setText(last + " / " + contentLength);
					progressBar.setValue(last);
				}
			};
			worker.execute();
		}
	}
}
