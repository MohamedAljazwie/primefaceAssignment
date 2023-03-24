package com.santechture.primefaceassignment.validator;

//package validator;
//
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.validator.FacesValidator;
//import javax.faces.validator.Validator;
//import javax.faces.validator.ValidatorException;
//
//import model.EmployeeDo;
//import sessions.EmployeeService;
//import util.STATUS;
//
//@FacesValidator(value = "codeValidator")
//public class EmployeeCodeValidator implements Validator {
//	@EJB
//	EmployeeService employeeSerice;
//
//	@Override
//	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//		String message = null;
//		String flag=(String)FacesContext.getCurrentInstance().getViewRoot().getViewMap().get("flag");
//        EmployeeDo employeeReturned=employeeSerice.getEmployeeByCode((String) value);
//
//		if (STATUS.PRESISTED.name().equals(flag)&&employeeReturned != null) {
//			message = "Duplicated Code ";
//		}
//		else if (STATUS.MODIFIED.name().equals(flag)&&employeeReturned != null) {
//			EmployeeDo selectedEmployee=(EmployeeDo)FacesContext.getCurrentInstance().getViewRoot().getViewMap().get("SelectedEmployee");
//              if(!employeeReturned.getEmpID().equals(selectedEmployee.getEmpID()))
//	      		message = " Duplicated Code ";
//		}
//		if (message != null) {
//			FacesMessage facesMessage = new FacesMessage();
//			facesMessage.setSummary(message);
//			throw new ValidatorException(facesMessage);
//		}
//	}
//
//}
