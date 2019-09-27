package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	private static final String PropertyConfiguration = null;
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="50975";   // HardCoded-Input for get details of single Employee and update Employee
    public Logger logger;
    
    @BeforeClass
    public void setup()
    {
    	logger=Logger.getLogger("EmployeesRestApi");
    	PropertyConfigurator.configure("log4j.properties");
    	logger.setLevel(Level.DEBUG);
    	
    	
    }
    
}