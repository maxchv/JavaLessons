package org.itstep;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.itstep.domain.Department;
import org.itstep.domain.Employee;
import org.itstep.domain.EmployeeInfo;
import org.itstep.domain.Responsibility;

/**
 * Created by User on 25.03.2017.
 */
public class Application {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        try (SessionFactory factory = configuration.buildSessionFactory();
                Session session = factory.openSession(); ) {

            //persistEmployee(session);
            getEmployee(session);
            //getEmployeeInfo(session);
        }

    }

    private static void getEmployeeInfo(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            EmployeeInfo info = session.load(EmployeeInfo.class, 1);
            System.out.println(info);
            System.out.println(info.getEmployee());



            tx.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    private static void getEmployee(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

//            Employee employee = session.load(Employee.class, 2);
//            System.out.println(employee);
//            List<Employee> empoyees = session.createQuery("from Employee", Employee.class).list();
//            empoyees.forEach(System.out::println);

            Department department = session.load(Department.class, 1);
            System.out.println(department);

            Employee employee = session.load(Employee.class, 1);
            System.out.println(employee);

            //System.out.println(employee);

            tx.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    private static void persistEmployee(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Department itDepartment = new Department();
            itDepartment.setName("IT");

            EmployeeInfo employeeInfo = new EmployeeInfo();
            employeeInfo.setAddress("К.Маркса, 101");

            Responsibility responsibility = new Responsibility();
            responsibility.setName("Выполнять заказы");

            Employee employee = new Employee();
            employee.setFirstName("Employee First Name");
            employee.setLastName("Employee Last Name");
            employee.setAge(30);
            employee.setInfo(employeeInfo);
            employee.setDepartment(itDepartment);
            employee.setResponsibilities(responsibility);

            responsibility.addEmployees(employee);

            employeeInfo.setEmployee(employee);

            employee.setInfo(employeeInfo);

            itDepartment.addEmployee(employee);

            session.persist(employeeInfo);
            session.save(responsibility);
            session.persist(employee);
            session.save(itDepartment);


            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
