package ua.itstep.shaptala.demo10;

import javax.swing.JOptionPane;

public class JOptionPaneDemo {

	public static void main(String[] args) {
		Object[] options = {"showInputDialog", "showConfirmDialog", "Отмена" };
		
		JOptionPane.showInputDialog(null, "Диалоги", "Заголовок", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		if(JOptionPane.showConfirmDialog(null, "Да/Нет/Отмена") == JOptionPane.YES_OPTION)
		{
			JOptionPane.showInputDialog("Ввод данных");						
		}
	}
}
