package com.employeeapi.testcases;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TC002_GET_SINGLE_EMPLOYEE_RECORD extends TestBase {

	//RequestSpecification httpRequest;
 
	@BeforeClass
	public void getEmployeeData() {
		logger.info(".....Testcase TC002_GET_SINGLE_EMPLOYEE_RECORD Started.... ");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees/" + empID);
	}

	@Test
	public void getResponseBody() {
		logger.info(".......Checking Response Body......");
		String responseBody = response.getBody().asString();
		logger.info("...Response Body..." + responseBody);
		Assert.assertTrue(responseBody != null && responseBody.contains(empID));

	}

	@Test
	public void checkStatusCode() {
		logger.info(".......Checking Status Code......");
		long statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}

	@Test
	public void checkResponseTime() {
		logger.info(".......Checking Response Time......");
		long responseTime = response.getTime();
		logger.info("...Response Time is..." + responseTime);
		if (responseTime > 2000) {
			logger.info("...Warning Response Time is greater then 2000...");

		}
		Assert.assertTrue(responseTime < 2000);
	}

	@Test
	public void checkStatusLine() {
		logger.info(".......Checking the Status Line........");
		String statusLine = response.getStatusLine();
		logger.info(".......Status Line is....." + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);

	}

	@Test
	public void checkContentLength() {
		logger.info("........Checking Content Length.....");
		String contentLength = response.header("Content-Length");
		logger.info("...Content Length is...=>" + contentLength);
		if (Integer.parseInt(contentLength) < 100) {
			logger.warn("....Content Length is less then 100...");
		}
		Assert.assertTrue(Integer.parseInt(contentLength) > 100);
	}

	@Test
	public void checkCookies() {
		logger.info("........Checking Cookies.....");
		String cookies = response.getCookie("PHPSESSID");

	}

	@Test
	public void checkServerType() {
		logger.info("....Checking Server....");
		String serverType = response.header("server");
		logger.info("........Server  Type is...." + serverType);
		Assert.assertEquals("Apache", serverType);

	}

	@Test
	public void checkContentEncoding() {
		logger.info("....Checking Content Encoding....");
		String contentEncoding = response.header("content-encoding");
		logger.info(".....Content Encoding is....." + contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);

	}

	@AfterClass
	public void tearDown() {
		logger.info("........Finished TC001_GET_ALL_EMPLOYEES.....");

	}

}
