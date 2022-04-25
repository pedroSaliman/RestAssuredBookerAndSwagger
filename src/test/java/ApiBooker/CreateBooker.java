package ApiBooker;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateBooker {
    RequestSpecification httprequest;
    Response response;

    @BeforeClass
    void api() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        httprequest = RestAssured.given();



        JSONObject hs = new JSONObject();
        hs.put("firstname","walled");
        hs.put("lastname","aliman");
        hs.put("totalprice",2000);
        hs.put("depositpaid",true);
        JSONObject bookingdate=new JSONObject();
        bookingdate.put("checkin","2018-02-02");
        bookingdate.put("checkout","2018-03-02");
        hs.put("bookingdates",bookingdate);
        hs.put("additionalneeds","Breakfast");
        httprequest.header("Content-Type","application/json");
        httprequest.body(hs.toJSONString());
        response = httprequest.request(Method.POST, "/booking");
    }
    @Test(priority = 1)
    void checkstatus(){
        int statuscode=response.getStatusCode();
        System.out.println(statuscode);
        Assert.assertEquals(200,statuscode);
    }
    @Test(priority = 2)
void checkbody()
    {

        String body = response.getBody().asString();
        System.out.println(body);
    }
    @Test(priority = 3)
    void checkheader(){
        String header = response.getHeader("content-type");
        System.out.println(header);
        Assert.assertEquals("application/json; charset=utf-8",header);
    }
    @Test(priority = 4)
    void checkstatusline()
    {
        String statusline = response.getStatusLine();
        System.out.println(statusline);
        Assert.assertEquals("HTTP/1.1 200 OK",statusline);
    }
}
