package SwaggerApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostUser {
    RequestSpecification httprequest;
    Response response;

    @BeforeClass
    void api() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        httprequest = RestAssured.given();


    }
    @Test(dataProvider = "data")
    void post(String fname) {

        httprequest.header("Content-Type", "application/json");
        httprequest.body(Data.user(fname));
        response = httprequest.request(Method.POST, "/user");
    }
    @Test(dependsOnMethods = "post")
    void checkstatus(){
        int code = response.getStatusCode();
        System.out.println(code);
        Assert.assertEquals(200,code);
    }
    @Test(dependsOnMethods = {"checkstatus"})
    void ceckbody(){
        String body = response.getBody().asString();
        System.out.println(body);
        String code = response.jsonPath().getString("code");
        Assert.assertEquals("200",code);
    }
    @Test(dependsOnMethods = {"ceckbody"})
    void checkheader(){
        String header = response.getHeader("content-type");
        System.out.println(header);
        Assert.assertEquals("application/json",header);
    }
    @Test(dependsOnMethods = {"checkheader"})
    void checkstatusline()
    {
        String statusline = response.getStatusLine();
        System.out.println(statusline);
        Assert.assertEquals("HTTP/1.1 200 OK",statusline);
    }



    @DataProvider(name = "data")
    Object[][] getdata(){

        Object[][] information = {{"walled"},
                {"medo"},
                {"kamal"},{"alla"},{"maha"},{"nsro"},{"kemo"},{"yas"},{"mohab"},{"bebo"},{"ledo"},{"you"},{"mohamed"}};

        return information;
    }
}
