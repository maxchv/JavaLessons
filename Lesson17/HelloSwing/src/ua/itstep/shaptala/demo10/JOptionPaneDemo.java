package ua.itstep.shaptala.demo10;

import javax.swing.JOptionPane;

public class JOptionPaneDemo {

	public static void main(String[] args) {
		Object[] options = {"showInputDialog", "showConfirmDialog", "������" };
		
		JOptionPane.showInputDialog(null, "�������", "���������", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		if(JOptionPane.showConfirmDialog(null, "��/���/������") == JOptionPane.YES_OPTION)
		{
			JOptionPane.showInputDialog("���� ������");						
		}
	}
}
