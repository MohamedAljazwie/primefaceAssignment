/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santechture.primefaceassignment.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santechture.primefaceassignment.model.DepartmentDo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mohamed.Aljazwiee
 */
@FacesConverter("DepartementConverter")
public class DepartementConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            ObjectMapper mapper=new ObjectMapper();
            DepartmentDo departmentDo =mapper.convertValue(value, DepartmentDo.class);
            departmentDo.setDepartmentName(value);
            return departmentDo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof DepartmentDo) {
            return ((DepartmentDo) value).getDepartmentName();
        }
        return null;
    }

}
