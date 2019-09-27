package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_PUT_EMPLOYEES_RECORD extends TestBase {

	@BeforeClass
	public void deleteEmployee() throws InterruptedException
	{
		logger.info("...........Started Execution of TestCase ID....");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		// First get the JSONPath Object Instance from the Response Interface
		
		JsonPath jsonPathEvulator=response.jsonPath();
		
		// Capture ID:
		
		String empID=jsonPathEvulator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/"+empID);
		Thread.sleep(50000);
		
	}
	 
	@Test
	public void checkStatusCode()
	{
		int statusCode=response.getStatusCode();  // Getting Status Code
		Assert.assertEquals(statusCode, 405);
	}
	@Test
	public void checkStatusLine()
	{
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 405 Method Not Allowed");
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
    	String serverType=response.header("Server ");
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
		logger.info("...............Ended TC004_POST_EMPLOYEES_RECORD.....");

    }

}
