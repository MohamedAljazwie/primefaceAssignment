/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santechture.primefaceassignment.bussiness;

import com.santechture.primefaceassignment.model.DepartmentDo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mohamed.Aljazwiee
 */
public interface DepartementService extends Serializable{
    List<DepartmentDo> getAllDepartements();
}
