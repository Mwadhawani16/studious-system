package test.testcase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestRestAPI {

    @Test
    public void testGetService(){
        int statusCode = RestAssured.get("http://myapi.com").statusCode();
        System.out.println("statusCode :"+statusCode);
    }

    @Test
    public void testGetService1(){
        RestAssured.baseURI = "http://myapi.com";
        Response res = RestAssured.get("/api/resource");
        System.out.println("statusCode :"+res.statusCode());
        System.out.println("data :"+res.asPrettyString());
    }
}
