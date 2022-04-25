package ApiBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetBooking {
    RequestSpecification httprequest;
    Response response;

    @BeforeClass
    void api() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        httprequest=  RestAssured.given();
        response = httprequest.request(Method.GET, "/booking");
        String id = response.jsonPath().getString("bookingid[0]");
        httprequest.expect().defaultParser(Parser.JSON);
        response = httprequest.request(Method.GET, "/booking/"+id+"");


    }
///////////////////////////////////////////STATUSCODE///////////////////////////////////////////////

    @Test
    void checkstatuscode() {
        int statuscode = response.getStatusCode();
        System.out.println("the status code is " + statuscode);
        Assert.assertEquals(200, statuscode);


    }
/////////////////////////////////////////BODY/////////////////////////////////////////////////////

    @Test(priority = 2)
    void checkbody(){
         Getmessage bdy = response.getBody().as(Getmessage.class);
        System.out.println(bdy.getLastname());
        System.out.println(bdy.getBookingdates().getCheckin());

   }
    /////////////////////////////////////////////////LINE/////////////////////////////////////////////

    @Test(priority = 3)
    void checkstatusline()
    {
        String statusline = response.getStatusLine();
        System.out.println(statusline);
        Assert.assertEquals("HTTP/1.1 200 OK",statusline);
    }
///////////////////////////////////////////////////////////////HEADER///////////////////////////////////

    @Test(priority = 4)
    void checkheader()
    {
        String header = response.getHeader("content-type");
        System.out.println(header);
        Assert.assertEquals("application/json; charset=utf-8",header);
    }

}
