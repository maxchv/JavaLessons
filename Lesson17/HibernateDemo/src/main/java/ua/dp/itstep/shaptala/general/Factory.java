package ua.dp.itstep.shaptala.general;

import ua.dp.itstep.shaptala.dao.StudentDao;
import ua.dp.itstep.shaptala.dao.impl.StudentDaoImpl;

public class Factory {
	private static Factory instance = null;
	private StudentDao studentDao = null;
	
	static {
		instance = new Factory();
	}
	
	public StudentDao getBookDao() {
		if(studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}
	
}
