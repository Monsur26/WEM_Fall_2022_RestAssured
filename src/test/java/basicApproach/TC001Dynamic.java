package basicApproach;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC001Dynamic {
    Response response;

    @BeforeMethod
    public void setup() throws InterruptedException {
        // baseURL connection
        RestAssured.baseURI="https://reqres.in/api/";

        //RequestSpecification
        RequestSpecification httpRequest= RestAssured.given();

        //Response
        response=httpRequest.request(Method.GET,"users");

        Thread.sleep(2000);
    }
    @Test
    public void statusCodevalidation(){
        //status Code
        int statusCode=response.getStatusCode();
        System.out.println("Status Code :"+ statusCode);

        //Assertion for statusCode
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void responseBodyValidation(){
        //ResponseBody
        String responseBody=response.getBody().asPrettyString();
        System.out.println(responseBody);

        //Assertion for ResponseBody
        Assert.assertEquals(responseBody.contains("Emma"),false);
    }
}
