package ua.itstep.shaptala.demo4;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFlowLayoutJPanel extends JFrame {
	
	
	public WindowFlowLayoutJPanel(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 100);
		
		// ������� ��� ���������� ��������� ����������
		JPanel panel = new JPanel();
		
		// �������� ����������������� ����������
		panel.setLayout(new FlowLayout());
		
		panel.add(new JButton("������"));
		panel.add(new JButton("+"));
		panel.add(new JButton("-"));
		panel.add(new JButton("������ � ������� ��������"));
		
		setContentPane(panel);
	}
}
