package com.santechture.primefaceassignment.bussiness;

import java.io.Serializable;
import java.util.List;

import com.santechture.primefaceassignment.model.*;
import com.santechture.primefaceassignment.util.Message;

public interface JPAService extends Serializable {

    public Message saveOrUpdateEmployee(Employee employee);

    public Message deleteEmployee(Employee employee);

    public Employee getEmployee(String queryName, List<Object> parameters);

    public List<Employee> getEmployees(String queryName, List<Object> parameters);

    public List<Employee> getEmployees(String queryName);
     public List<Department> getAllDepartements() ;

}
