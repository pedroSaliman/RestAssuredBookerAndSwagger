package ApiBooker;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.IOException;

public class PUTBOOKER {
    RequestSpecification httprequest;
    Response response;

    @BeforeClass
    void api() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        httprequest = RestAssured.given();


    }
    //////////////////////////////////////////////////POSTDATA FOR UPDATE/////////////////////////////////////////////

    @Test(dataProvider = "Excel")
    void putdata(String fname,String lname,String needs){

        response=httprequest.request(Method.GET,"/booking");
        String booking = response.jsonPath().getString("bookingid[0]");
        /////

        JSONObject hs = new JSONObject();
        hs.put("firstname",fname);
        hs.put("lastname",lname);
        hs.put("totalprice",2000);
        hs.put("depositpaid",true);
        JSONObject bookingdate=new JSONObject();
        bookingdate.put("checkin","2018-02-02");
        bookingdate.put("checkout","2018-03-02");
        hs.put("bookingdates",bookingdate);
        hs.put("additionalneeds",needs);
        httprequest.header("Content-Type","application/json");
        httprequest.body(hs.toJSONString());


///
        httprequest.auth().preemptive().basic("admin","password123");
        response = httprequest.request(Method.PUT, "/booking/"+booking+"");

    }
    //////////////////////////////////////////////////STATUSCODE/////////////////////////////////////////////

    @Test(dependsOnMethods = {"putdata"})
    void checkstatuscode() {
        int statuscode = response.getStatusCode();
        System.out.println("the status code is " + statuscode);
        Assert.assertEquals(200, statuscode);


    }
    //////////////////////////////////////////////////BODY/////////////////////////////////////////////

    @Test(dependsOnMethods = {"checkstatuscode"})
    void checkbody(){
        String body = response.getBody().asString();
        System.out.println(body);
        String name = response.jsonPath().getString("firstname");
        Assert.assertEquals("ahmed",name);

    }
    //////////////////////////////////////////////////LINE/////////////////////////////////////////////

    @Test(dependsOnMethods = {"checkbody"})
    void checkstatusline()
    {
        String statusline = response.getStatusLine();
        System.out.println(statusline);
        Assert.assertEquals("HTTP/1.1 200 OK",statusline);
    }
 //////////////////////////////////////////////////HEADER/////////////////////////////////////////////
    @Test(dependsOnMethods = {"checkstatusline"})
    void checkheader()
    {
        String header = response.getHeader("content-type");
        System.out.println(header);
        Assert.assertEquals("application/json; charset=utf-8",header);
    }
  ///////////////////////////////////////DATAPROVIDER/////////////////////////////////////////////////////
  @DataProvider(name = "Excel")
  public Object[][] getExcel() throws IOException {
      DataFormatter format = new DataFormatter();
      String path ="E:\\seleniumprojects\\FinalRestAssuredProjectApi\\src\\test\\java\\ApiBooker\\ApiData.xlsx";
      FileInputStream fs = new FileInputStream(path);
      XSSFWorkbook wb = new XSSFWorkbook(fs);
      XSSFSheet sheet = wb.getSheet("sheet1");
      int rowcount = sheet.getPhysicalNumberOfRows();
      XSSFRow row = sheet.getRow(0);
      int colum = row.getLastCellNum();
      Object data[][] = new Object[rowcount - 1][colum];
      for (int i = 0; i < rowcount - 1; i++) {
          row = sheet.getRow(i + 1);
          for (int j = 0; j < colum; j++) {
              XSSFCell cell = row.getCell(j);
              data[i][j] = format.formatCellValue(cell);
          }
      }


      return data;
  }
}
