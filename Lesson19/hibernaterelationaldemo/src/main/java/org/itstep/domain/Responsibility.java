package org.itstep.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Responsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idResponsibility;

    String name;

    @ManyToMany
    Set<Employee> employees;

    public Integer getIdResponsibility() {
        return idResponsibility;
    }

    public void setIdResponsibility(Integer idResponsibility) {
        this.idResponsibility = idResponsibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployees(Employee employee) {
        this.employees.add(employee);
    }

    public Responsibility() {
        employees = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Responsibility{" +
                "idResponsibility=" + idResponsibility +
                ", name='" + name + '\'' +
                '}';
    }
}
