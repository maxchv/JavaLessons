package ua.itstep.shaptala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;

public class JProgressWindowDemo {

	private JFrame frame;
	private boolean process;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JProgressWindowDemo window = new JProgressWindowDemo();
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
	public JProgressWindowDemo() {
		initialize();
		process = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		progressBar.setBounds(55, 170, 349, 22);
		frame.getContentPane().add(progressBar);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(!process) {
					btnNewButton.setText("Stop");
					SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

						@Override
						protected Boolean doInBackground() throws Exception {
							for(int i=0; i<=10; i++) {
								publish(i);
								Thread.sleep(1000);
							}
							return true;
						}
						
						@Override
						protected void done() {
							try {
								Boolean status = get();
								if(status) {
									btnNewButton.setText("Start");
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						};
						
						protected void process(List<Integer> chunks) {
							progressBar.setValue(chunks.get(chunks.size() - 1)*10);
						};
						
					};
					worker.execute();
				}
			}
		});
		btnNewButton.setBounds(177, 93, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
