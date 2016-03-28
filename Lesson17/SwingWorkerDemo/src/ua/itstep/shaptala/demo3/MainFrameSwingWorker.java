package ua.itstep.shaptala.demo3;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MainFrameSwingWorker extends JFrame {

	private JPanel contentPane;
	private JLabel lblCounter;
	private JLabel lblStatus;
	private JButton btnStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameSwingWorker frame = new MainFrameSwingWorker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrameSwingWorker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblCounter = new JLabel("0");
		lblCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter.setBounds(79, 77, 74, 38);
		lblCounter.setFont(new Font("Tahoma", Font.PLAIN, 23));

		lblStatus = new JLabel("Task not completed");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(10, 168, 202, 14);

		btnStart = new JButton("Start");
		btnStart.setBounds(69, 226, 93, 32);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblCounter);
		contentPane.add(lblStatus);
		contentPane.add(btnStart);
	}

	protected void start() {
		if (btnStart.getText().matches("Start")) {
			btnStart.setText("Stop");
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					for (int i = 0; i <= 10; i++) {
						lblCounter.setText(Integer.toString(i));
						Thread.sleep(1000);
					}
					lblStatus.setText("Completed");
					btnStart.setText("Start");
					return null;
				}
			};
			worker.execute();
		}
	}
}
