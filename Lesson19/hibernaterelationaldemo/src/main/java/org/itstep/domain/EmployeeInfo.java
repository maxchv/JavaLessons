package org.itstep.domain;

import javax.persistence.*;

@Entity
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmployeeInfo;

    String address;

    @OneToOne
    Employee employee;

    public Integer getIdEmployeeInfo() {
        return idEmployeeInfo;
    }

    public void setIdEmployeeInfo(Integer id) {
        this.idEmployeeInfo = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "id=" + idEmployeeInfo +
                ", address='" + address + '\'' +
                '}';
    }
}
