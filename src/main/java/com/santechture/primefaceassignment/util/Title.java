package com.santechture.primefaceassignment.util;

import java.util.HashMap;
import java.util.Map;

public enum Title {
EMPLOYEES ("employees"),
GETEMPLOYEEBYCODE("getEmployeeByCode"),
GETEMPLOYEEBYEMAIL("getEmployeeByEmail"),
SAVEORUPDATEEMPLOYEE("saveOrUpdateEmployee"),
DELETEEMPLOYEE("deleteEmployee");

	private static final Map<String,Title> titles=new HashMap<>();
	static {
		for(Title value:Title.values())
			titles.put(value.value, value);
	}
	private String value;
	private Title(String value) {
	  this.value=value;
	}
	public String value() {
		return value;
	}
   public  static Title  get(String value)
   {
	   return titles.get(value);
   }
	
}
