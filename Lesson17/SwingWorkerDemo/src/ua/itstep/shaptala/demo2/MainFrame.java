package ua.itstep.shaptala.demo2;

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

public class MainFrame extends JFrame {

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
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
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
			Thread worker = new Thread() {
				public void run() {
					// ����� ������
					for (int i = 0; i <= 10; i++) {
						try {
							final int count = i;
							SwingUtilities.invokeAndWait(new Runnable() {
								public void run() {
									lblCounter.setText(Integer.toString(count));
								}
							});
							Thread.sleep(1000);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							lblStatus.setText("Completed.");
							btnStart.setText("Start");
						}
					});
				}
			};
			worker.start();
		}
	}
}
