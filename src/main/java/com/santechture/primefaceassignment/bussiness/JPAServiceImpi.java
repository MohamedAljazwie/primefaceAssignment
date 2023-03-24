package com.santechture.primefaceassignment.bussiness;

import com.santechture.primefaceassignment.model.Department;
import com.santechture.primefaceassignment.model.DepartmentDo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.santechture.primefaceassignment.model.Employee;
import com.santechture.primefaceassignment.util.Factory;
import com.santechture.primefaceassignment.util.Message;
import com.santechture.primefaceassignment.util.STATUS;
import com.santechture.primefaceassignment.util.Title;
import java.util.ArrayList;

@SessionScoped
public class JPAServiceImpi implements JPAService {

    private SessionFactory sessionFactory;
  

    JPAServiceImpi() {
        sessionFactory = Factory.getSessionFactory();
    }

    @Override
    public Message saveOrUpdateEmployee(Employee employee) {

        Message<Object> message = new Message<Object>();
        message.setTitle(Title.SAVEORUPDATEEMPLOYEE);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            employee.setEmpStatus(STATUS.ACTIVE.name());
            employee = (Employee) session.merge(employee);
            boolean presisted = employee.getEmpID() == null ? true : false;

            message.setStatus(STATUS.SUCCESS.name());
            message.setMessage("The Employee " + (presisted ? "added" : "modified") + "Successfully");
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus(STATUS.FAILD.name());
            message.setMessage(e.getCause());
            transaction.rollback();

        }
//        finally{
//        if(session!=null)
//            session.close();
//        }
        return message;
    }

    @Override
    public Message deleteEmployee(Employee employee) {
        Message<Object> message = new Message<Object>();
        message.setTitle(Title.DELETEEMPLOYEE);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            employee = (Employee) session.merge(employee);
            employee.setEmpStatus(STATUS.INACTIVE.name());
            message.setStatus(STATUS.SUCCESS.name());
            message.setMessage("The Employee removed Successfully");
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus(STATUS.FAILD.name());
            message.setMessage(e.getCause());
            transaction.rollback();
        }
//        finally{
//        if(session!=null)
//            session.close();
//        }
        return message;

    }

    @Override
    public Employee getEmployee(String queryName, List<Object> parameters) {
        return getSingleResult(queryName, parameters.toArray());
    }

    @Override
    public List<Employee> getEmployees(String queryName, List<Object> parameters) {
        return getResultList(queryName, parameters.toArray());
    }

    @Override
    public List<Employee> getEmployees(String queryName) {
        return getResultList(queryName);
    }

    @Override
    public List<Department> getAllDepartements() {
        List<Department> departments= getResultList("Departments.findAll");
        if(departments==null)
            return new ArrayList<>();
        return departments;
    }
    

    private boolean validateData(Employee employee, Message message) {
        Map<String, String> messageData = new HashMap<>();
        Employee employeeReturned = getEmployee("Employees.findByEmployeeCode",
                Arrays.asList(new Object[]{employee.getGender()}));
        if (employeeReturned != null) {
            if (!employeeReturned.getEmpID().equals(employee.getEmpID())) {
                messageData.put("empCode", "Duplicated Code");
            }
        }

        employeeReturned = getEmployee("Employees.findByEmail", Arrays.asList(new Object[]{employee.getEmpEmail()}));
        if (employeeReturned != null) {
            if (!employeeReturned.getEmpID().equals(employee.getEmpID())) {
                messageData.put("empEmail", "Email  Already Exist");
            }
        }

        if (messageData.size() > 0) {
            message.setMessage(messageData);
            message.setStatus(STATUS.FAILD.name());
            return false;
        }

        return true;
    }

    private <T> T getSingleResult(String queryName, Object... paramValues) {
        try {
            return (T) createQuery(queryName, paramValues).list().get(0);
        } catch (NoResultException e) {
            return null;
        }
    }

    private <T> List<T> getResultList(String queryName, Object... paramValues) {
        try {
            return createQuery(queryName, paramValues).list();
        } catch (NoResultException e) {
            return null;
        }
    }

    private Query createQuery(String queryName, Object... paramValues) {
       Session session=null;
       
         session = sessionFactory.openSession();
        Query query = session.getNamedQuery(queryName);
        int count = 0;
        for (Object paramValue : paramValues) {
            query.setParameter(count++, paramValue);
        }
        return query;
      
    }
}
