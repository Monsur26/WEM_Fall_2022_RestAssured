package basicApproach;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001GET {
    @Test
    public void alluserGETCALL(){
        // baseURL connection

        RestAssured.baseURI="https://reqres.in/api/";

        //RequestSpecification

        RequestSpecification httpRequest= RestAssured.given();

        //Response
        Response response=httpRequest.request(Method.GET,"users");

        //status Code
        int statusCode=response.getStatusCode();
        System.out.println("Status Code :"+ statusCode);

        //Assertion for statusCode
        Assert.assertEquals(statusCode,200);

        //ResponseBody
        String responseBody=response.getBody().asPrettyString();
        System.out.println(responseBody);

        //Assertion for ResponseBody
        Assert.assertEquals(responseBody.contains("Emma"),true);

        //Status Line
        String statusLine=response.getStatusLine();
        System.out.println("Status line :"+ statusLine);

        //Assertion for Status line
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

        //ResponseTime
        long responseTime=response.time();
        //Assertion of response Time
        System.out.println(responseTime);
        Assert.assertTrue(responseTime<1000);

        //Header
        String header=response.header("Content-Type");
        System.out.println("Header value is :" +header);

        //Assertion of Header
        Assert.assertEquals(header,"application/json; charset=utf-8");

    }
}
