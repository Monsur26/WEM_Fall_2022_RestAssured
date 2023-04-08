package basicApproach;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002 {

    @Test
    public void newUserPOST(){
        // baseURL connection
        RestAssured.baseURI="https://reqres.in/api/";

        //RequestSpecification
        RequestSpecification httpRequest= RestAssured.given();

        //Jsonformetter to get the payload object
        JSONObject reqParam= new JSONObject();
        reqParam.put("first_name","Aniqa");
        reqParam.put("last_name","Miazi");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(reqParam.toJSONString());

        Response response=httpRequest.request(Method.POST,"users");

/*        String responseBody=response.getBody().asPrettyString();
        System.out.println(responseBody);*/


        //status Code
        int statusCode=response.getStatusCode();
        System.out.println("Status Code :"+ statusCode);

        //Assertion for statusCode
        Assert.assertEquals(statusCode,201);

        //ResponseBody
        String responseBody=response.getBody().asPrettyString();
        System.out.println(responseBody);

        //Assertion for ResponseBody
        Assert.assertEquals(responseBody.contains("Aniqa"),true);

        //Status Line
        String statusLine=response.getStatusLine();
        System.out.println("Status line :"+ statusLine);

        //Assertion for Status line
        Assert.assertEquals(statusLine.contains("HTTP/1.1"),true);

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
