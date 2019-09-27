package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_POST_EMPLOYEES_RECORD extends TestBase {
	
	
	String empname=RestUtils.empName(); 
	String empage=RestUtils.empAge();
	String empsal=RestUtils.empSal();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException 
	{
		logger.info("...............Started TC003_POST_EMPLOYEES_RECORD.....");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		JSONObject requestParam=new JSONObject();
		requestParam.put("name", empname);
		requestParam.put("salary",empsal);
		requestParam.put("age",empage);

		// Add a header requesting that the header body is JSON
		httpRequest.header("Content-Type","application/json");
		// Add JSON to the Body of Requset
		httpRequest.body(requestParam.toJSONString());
		// JSONObject is a class which represent a simple JSON.we can add the key value pair by using put method=
		response=httpRequest.request(Method.POST,"/create");
		Thread.sleep(5000);
	}
	@Test
	public void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empname), true);
		Assert.assertEquals(responseBody.contains(empsal), true);
		Assert.assertEquals(responseBody.contains(empage), true);
	}
	@Test
	public void checkStatusCode()
	{
		int statusCode=response.getStatusCode();  // Getting Status Code
		Assert.assertEquals(statusCode, statusCode);
	}
	@Test
	public void checkStatusLine()
	{
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
    @Test
    public void checkContentType()
    {
    	String contentType=response.header("Content-Type");
    	Assert.assertEquals(contentType,"text/html;charset=UTF-8");
    }
    @Test
    public void checkServerType()
    {
    	String serverType=response.header("server ");
    	logger.info("...........Server Type....."+serverType);
    	Assert.assertEquals(serverType, "Apache");
    }
    
    @Test
    public void checkContentEncoding()
    {
    	String contentEncoding=response.header("content-encoding");
    	Assert.assertEquals(contentEncoding, "gzip");
    }
    @BeforeClass
    public void tearDown()
    {
		logger.info("...............Ended TC003_POST_EMPLOYEES_RECORD.....");

    }
}
