package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empName()
	{
		String generatedNameString=RandomStringUtils.randomAlphabetic(1);
		return ("Jhon"+generatedNameString);
	} 
	public static String empAge()
	{
		String generatedAge=RandomStringUtils.randomNumeric(2);
		return generatedAge;
	}
	public static String empSal()
	{
		String generatedSalString=RandomStringUtils.randomNumeric(5);
		return (generatedSalString);
	}
}
