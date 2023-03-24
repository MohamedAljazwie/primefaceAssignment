package com.santechture.primefaceassignment.bussiness;

import java.io.Serializable;
import java.util.List;


import com.santechture.primefaceassignment.model.EmployeeDo;
import com.santechture.primefaceassignment.util.Message;
import javax.enterprise.context.SessionScoped;
@SessionScoped
public interface EmployeeService extends Serializable  {
	public Message saveOrUpdateEmployee(EmployeeDo employeeDo);

	public Message deleteEmployee(EmployeeDo EmployeeDo);
	
	public Message getEmployeeByEmail(String email);
	public Message getEmployeeByCode(String code);
	public Message  getEmployees();
}
