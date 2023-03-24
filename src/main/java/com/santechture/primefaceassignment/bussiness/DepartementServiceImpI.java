/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santechture.primefaceassignment.bussiness;

import com.santechture.primefaceassignment.model.Department;
import com.santechture.primefaceassignment.model.DepartmentDo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Mohamed.Aljazwiee
 */
@Named
@SessionScoped
public class DepartementServiceImpI implements DepartementService {

    @Inject
    private JPAService jpaService;

    @Override
    public List<DepartmentDo> getAllDepartements() {
        return getList(jpaService.getAllDepartements().iterator());
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
        if (DO instanceof Department) {
            return (T) getDo((Department) DO);
        }
        return null;
    }

    private DepartmentDo getDo(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDo departmentDo = new DepartmentDo();
        departmentDo.setDepartmentId(department.getDepartmentId());
        departmentDo.setDepartmentName(department.getDepartmentName());
        return departmentDo;
    }
}
