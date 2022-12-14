package get_request;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
  }
 */

    @Test
    public void test() {
        //set the url
        spec.pathParams("first","booking","second",91);
        //set the expeceted data
        Map<String,String>bookingdatesMap = new HashMap<String,String>();
        bookingdatesMap.put("checkin","2013-02-23");
        bookingdatesMap.put("checkout","2014-10-23");

        Map<String,Object>expectedData = new HashMap<String,Object>();

        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println(expectedData);

        //set the request
       Response response= given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       //Do assertions
      Map<String,Object>actualData=response.as(HashMap.class);
      assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
      assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
      assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
      assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
      assertEquals(bookingdatesMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
      //key -value ikilileri string -object seklinde oldugundan ,Bookingdata data kismini  casting ile map yaptik
        assertEquals(bookingdatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
      assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }
}
