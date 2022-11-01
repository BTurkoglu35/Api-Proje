package Calisma;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfullData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalismaTestData extends RestfulBaseUrl {
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
  "additionalneeds": "c"
}
}
*/
    @Test
    public void test() {
        spec.pathParams("first", "booking", "second", 91);

        //set the expected data
        RestfullData obj = new RestfullData();
        Map<String, Object> expectedData = obj.dataMethod("Sally", "Brown", 111, true, "Breakfast");
        Map<String, String> bookingDateMap = obj.bookindatesMethod("2013-02-23", "2014-10-23");

        //set the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //  response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        // System.out.println(actualData);

        //do assertions
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingDateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
    }

    @Test
    public void test2() {
        spec.pathParams("first","booking","second",91);

        Response response= given().spec(spec).when().get("/{first}/{second}");
      //  response.prettyPrint();

        JsonPath json=response.jsonPath();
        json.prettyPrint();

        assertEquals("Sally",json.getString("firstname"));
        assertEquals("Brown", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2013-02-23",json.getString("bookingdates.checkin"));
        assertEquals("2014-10-23",json.getString("bookingdates.checkout"));
        assertEquals("Breakfast",json.getString("additionalneeds"));

    }
}
