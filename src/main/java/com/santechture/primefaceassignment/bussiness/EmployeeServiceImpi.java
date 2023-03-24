package com.santechture.primefaceassignment.bussiness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.santechture.primefaceassignment.model.*;
import com.santechture.primefaceassignment.util.Message;
import com.santechture.primefaceassignment.util.STATUS;
import com.santechture.primefaceassignment.util.Title;
import javax.inject.Named;

@SessionScoped
@Named
public class EmployeeServiceImpi implements EmployeeService {

    @Inject
    private JPAService jpaService;

    public EmployeeServiceImpi() {
        // jpaService=new JPAServiceImpi();
    }

    @Override
    public Message saveOrUpdateEmployee(EmployeeDo employeeDo) {
        return jpaService.saveOrUpdateEmployee(new Employee(employeeDo));

    }

    @Override
    public Message deleteEmployee(EmployeeDo employeeDo) {
        return jpaService.deleteEmployee(new Employee(employeeDo));

    }

    @Override
    public Message getEmployeeByEmail(String email) {
        EmployeeDo employeeDo = getDo(jpaService.getEmployee("Employees.findByEmail", Arrays.asList(new Object[]{email})));
        Message message = new Message();
        message.setMessage(employeeDo);
        message.setStatus(STATUS.SUCCESS.name());
        message.setTitle(Title.EMPLOYEES);
        return message;
    }

    @Override
    public Message getEmployeeByCode(String code) {
        EmployeeDo employeeDo = getDo(jpaService.getEmployee("Employees.findByEmployeeCode", Arrays.asList(new Object[]{code})));
        Message message = new Message();
        message.setMessage(employeeDo);
        message.setStatus(STATUS.SUCCESS.name());
        message.setTitle(Title.EMPLOYEES);
        return message;
    }

    @Override
    public Message getEmployees() {
        List<EmployeeDo> employeeDos = getList(jpaService.getEmployees("Employees.findAll", Arrays.asList(new Object[]{STATUS.ACTIVE.name()})).iterator());
        Message<List<EmployeeDo>> message = new Message();
        message.setMessage(employeeDos);
        message.setStatus(STATUS.SUCCESS.name());
        message.setTitle(Title.EMPLOYEES);
        return message;
    }

    private <T> List<T> getList(Iterator<?> iterator) {
        List<T> elements = new ArrayList<T>();
        try {
            while (iterator.hasNext()) {
                elements.add((T) getDo(iterator.next()));
            }
            return elements;
        } catch (Exception e) {
            return null;
        }

    }

    private <T> T getDo(Object DO) {
        if (DO instanceof Employee) {
            return (T) getDo((Employee) DO);
        }
        return null;
    }

    private EmployeeDo getDo(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDo employeeDo = new EmployeeDo();
        employeeDo.setEmpID(employee.getEmpID());
        employeeDo.setGender(employee.getGender());
        employeeDo.setEmpEmail(employee.getEmpEmail());
        employeeDo.setEmpName(employee.getEmpName());
        DepartmentDo departmentDo=new DepartmentDo();
        if(employee.getDepartment()!=null)
        {
        departmentDo.setDepartmentId(employee.getDepartment().getDepartmentId());
        departmentDo.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        employeeDo.setDepartment(departmentDo);
        return employeeDo;
    }

}
