package com.santechture.primefaceassignment.mangedbean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santechture.primefaceassignment.bussiness.DepartementService;
import com.santechture.primefaceassignment.bussiness.EmployeeService;
import com.santechture.primefaceassignment.model.DepartmentDo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//import org.primefaces.context.RequestContext;
import com.santechture.primefaceassignment.model.EmployeeDo;
import com.santechture.primefaceassignment.util.Message;
import com.santechture.primefaceassignment.util.STATUS;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named(value = "mbEmployee")
@javax.enterprise.context.SessionScoped
public class EmployeeMangedBean implements Serializable {

    private EmployeeDo selectedEmployee;
    private List<EmployeeDo> employees;
    private List<DepartmentDo> departmentDos;
    private String flag;
    private String departmentName;
    private String jsonData;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private DepartementService departementService;

    @PostConstruct
    private void init() {
        employees = new ArrayList();
        selectedEmployee = new EmployeeDo();
        selectedEmployee.setDepartment(new DepartmentDo());
        flag = STATUS.UNKNOWN.name();
        employees = (List<EmployeeDo>) employeeService.getEmployees().getMessage();
        departmentDos = departementService.getAllDepartements();

    }

    public void submit(DepartmentDo departmentDo) {
        selectedEmployee.setDepartment(getSelectedDepartmentDo());
        Message<Object> message = employeeService.saveOrUpdateEmployee(selectedEmployee);
        showFacesMessage(message);
        if (flag.equals(STATUS.PRESISTED.name())) {
            employees = (List<EmployeeDo>) employeeService.getEmployees().getMessage();
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlg1').hide();");
    }

    private DepartmentDo getSelectedDepartmentDo() {
        for (DepartmentDo departmentDo : departmentDos) {
            if (departmentDo.getDepartmentName().equals(departmentName)) {
                return departmentDo;
            }
        }
        return new DepartmentDo(departmentName);
    }

    public void createNewUser() {
        this.selectedEmployee = new EmployeeDo();
        selectedEmployee.setDepartment(new DepartmentDo());
        flag = STATUS.PRESISTED.name();
    }

    public void setUpdateemployee(EmployeeDo employee) {
        this.selectedEmployee = employee;
        flag = STATUS.MODIFIED.name();

    }

    public void setSelectedDepartment(ValueChangeEvent event) {
        departmentName =(String)event.getNewValue();

    }

    private void showFacesMessage(Message message) {
        FacesMessage facesMessage = new FacesMessage(
                (message.getStatus().equals(STATUS.SUCCESS.name()) ? FacesMessage.SEVERITY_INFO
                : FacesMessage.SEVERITY_ERROR),
                message.getMessage().toString(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public EmployeeDo getSelectedEmployee() {
        return selectedEmployee;
    }

    public void removeemployee(EmployeeDo employee) {
        flag = STATUS.REMOVED.name();
        selectedEmployee=employee;
        Message<Object> message = employeeService.deleteEmployee(selectedEmployee);
        if (message.getStatus().equals(STATUS.SUCCESS.name())) {
            employees.remove(employee);
        }
        showFacesMessage(message);

    }

    public void setmployee(EmployeeDo selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<EmployeeDo> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDo> employees) {
        this.employees = employees;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public List<DepartmentDo> getDepartmentDos() {
        return departmentDos;
    }

    public void setDepartmentDos(List<DepartmentDo> departmentDos) {
        this.departmentDos = departmentDos;
    }

    public String getDepartmentId() {
        return departmentName;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentName = departmentId;
    }

}
