/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santechture.primefaceassignment.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohamed.aljazwiee
 */
@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employee e where e.empStatus =? ")
})

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "empid", sequenceName = "Employee_Empid", initialValue = 100, allocationSize = 1)
    @GeneratedValue(generator = "empid", strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID", length = 10, nullable = false, unique = true, updatable = false)
    private Integer empID;

    @Column(name = "EMPLOYEE_NAME", length = 100, nullable = true, unique = false, updatable = true)
    private String empName;
    @Column(name = "EMPLOYEE_EMAIL", length = 100, nullable = false, updatable = true)
    private String empEmail;

    @Column(name = "GENDER", length = 100, nullable = false, updatable = true)
    private String gender;

    
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "EMPLOYEE_STATUS", length = 100, nullable = false, unique = false, updatable = true)
    private String empStatus;

    public Employee() {
    }

    public Employee(EmployeeDo employeeDo) {
        setEmpID(employeeDo.getEmpID());
        setGender(employeeDo.getGender());
        setEmpEmail(employeeDo.getEmpEmail());
        setEmpName(employeeDo.getEmpName());
        Department department=new Department();
        department.setDepartmentId(employeeDo.getDepartment().getDepartmentId());
        department.setDepartmentName(employeeDo.getDepartment().getDepartmentName());
        setDepartment(department);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

}
