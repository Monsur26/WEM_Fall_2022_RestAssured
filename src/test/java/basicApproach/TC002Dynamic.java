package basicApproach;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC002Dynamic {
    Response response;
    @BeforeMethod
    public void setup() throws InterruptedException {
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


        response=httpRequest.request(Method.POST,"users");

        Thread.sleep(2000);
    }
    @Test
    public void statusCodeTest(){
        //status Code
        int statusCode=response.getStatusCode();
        System.out.println("Status Code :"+ statusCode);

        //Assertion for statusCode
        Assert.assertEquals(statusCode,201);
    }
    @Test
    public void responseBodyValidation(){
        //ResponseBody
        String responseBody=response.getBody().asPrettyString();
        System.out.println(responseBody);

        //Assertion for ResponseBody
        Assert.assertEquals(responseBody.contains("Aniqa"),true);
    }
}
