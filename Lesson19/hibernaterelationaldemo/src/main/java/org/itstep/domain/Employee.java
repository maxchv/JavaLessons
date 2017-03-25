package org.itstep.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmployee;

    @OneToOne(mappedBy = "employee")
    EmployeeInfo info;

    @ManyToOne
    Department department;

    @ManyToMany(mappedBy = "employees")
    Set<Responsibility> responsibilities;

    String firstName;

    String lastName;

    Integer age;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer id) {
        this.idEmployee = id;
    }

    public EmployeeInfo getInfo() {
        return info;
    }

    public void setInfo(EmployeeInfo info) {
        this.info = info;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(Responsibility responsibilitie) {
        this.responsibilities.add(responsibilitie);
    }

    public Employee() {
        responsibilities = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", info=" + info +
                ", responsibilities=" + responsibilities +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
