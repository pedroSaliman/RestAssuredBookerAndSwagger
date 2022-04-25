package ApiBooker;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteBooking {
    RequestSpecification httprequest;
    Response response;

    @BeforeClass
    void api() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        httprequest = RestAssured.given();


        response=httprequest.request(Method.GET,"/booking");
        String booking = response.jsonPath().getString("bookingid[0]");


        httprequest.auth().preemptive().basic("admin","password123");
        response = httprequest.request(Method.DELETE, "/booking/"+booking+"");

    }

    @Test
    void checkstatuscode() {
        int statuscode = response.getStatusCode();
        System.out.println("the status code is " + statuscode);
        Assert.assertEquals(201, statuscode);


    }

    @Test(priority = 2)
    void checkbody(){
        String body = response.getBody().asString();
        System.out.println(body);


    }
    @Test(priority = 3)
    void checkstatusline()
    {
        String statusline = response.getStatusLine();
        System.out.println(statusline);
        Assert.assertEquals("HTTP/1.1 201 Created",statusline);
    }
    @Test(priority = 4)
    void checkheader()
    {
        String header = response.getHeader("content-type");
        System.out.println(header);
        Assert.assertEquals("text/plain; charset=utf-8",header);
    }
}
