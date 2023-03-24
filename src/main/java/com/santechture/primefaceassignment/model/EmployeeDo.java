package com.santechture.primefaceassignment.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EmployeeDo implements Serializable {

    private Integer empID;
    private String empName;
    private String empEmail;
    private String gender;
    private DepartmentDo department;
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

    public DepartmentDo getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDo department) {
        this.department = department;
    }



 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((empEmail == null) ? 0 : empEmail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EmployeeDo other = (EmployeeDo) obj;
        if (gender == null) {
            if (other.gender != null) {
                return false;
            }
        } else if (!gender.equals(other.gender)) {
            return false;
        }
        if (empEmail == null) {
            if (other.empEmail != null) {
                return false;
            }
        } else if (!empEmail.equals(other.empEmail)) {
            return false;
        }
        return true;
    }

}
