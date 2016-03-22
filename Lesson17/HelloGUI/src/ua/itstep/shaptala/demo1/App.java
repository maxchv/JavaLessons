package ua.itstep.shaptala.demo1;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

public class App {

	public static void main(String[] args) {
		
//		JFrame frame = new JFrame("���������� �������");
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(300, 200);
//		frame.setVisible(true);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MyFrame frame = new MyFrame("Hello");
				frame.setVisible(true);
			}
		});
		
	}
}

class MyFrame extends JFrame implements MouseListener {
	
	JFrame _this;
	
	MyFrame(String title) {
		super(title);
		
		_this = this;
		
		JButton btn = new JButton("������ 1");
		
		getContentPane().add(btn, BorderLayout.NORTH);		
		getContentPane().add(new JButton("������ 2"), BorderLayout.WEST);
		getContentPane().add(new JButton("������ 3"), BorderLayout.EAST);
		getContentPane().add(new JButton("������ 4"), BorderLayout.SOUTH);
		getContentPane().add(new JButton("������ 5"), BorderLayout.CENTER);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		
		addMouseListener(this);
		
		// ������������� �� ������� �������� ����
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				_this.setTitle("X: " + e.getX() + " Y: " + e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)	{
			JOptionPane.showMessageDialog(this, "����� ������?");
		} else if(e.getButton() == MouseEvent.BUTTON2) {
			Object[] options = {"������", "�����", "������"};
			Object name = JOptionPane.showInputDialog(this, "�� ���?", "������", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			JOptionPane.showMessageDialog(this, "������ " + name);
		} else {
			JOptionPane.showConfirmDialog(this, "������ ���������","������ ���������", JOptionPane.YES_NO_OPTION);
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
