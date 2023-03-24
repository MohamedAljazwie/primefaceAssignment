package com.santechture.primefaceassignment.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public static boolean validateEmail(String emial)
	{
		if(emial!=null&&!emial.isEmpty())
		{
		Pattern pattern = Pattern
				.compile("^[\\w\\.-_\\+]+@[\\w-]+(\\.\\w{2,3})+$");
		Matcher matcher = pattern.matcher(emial);
		if(matcher.matches())
			return true;
		}
		return false;
	}
}
