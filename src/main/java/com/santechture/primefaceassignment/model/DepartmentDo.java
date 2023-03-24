package com.santechture.primefaceassignment.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author MohamedSayed
 */
public class DepartmentDo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer departmentId;
    private String departmentName;

    public DepartmentDo(Integer departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DepartmentDo() {
    }

    public DepartmentDo(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentDo(String departmentName) {
        this.departmentName = departmentName;
    }
    
    
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.departmentId);
        hash = 41 * hash + Objects.hashCode(this.departmentName);
        return hash;
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
        final DepartmentDo other = (DepartmentDo) obj;
        if (!Objects.equals(this.departmentName, other.departmentName)) {
            return false;
        }
        if (!Objects.equals(this.departmentId, other.departmentId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + '}';
    }
   

    
}
