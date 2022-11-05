package post_request;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfullData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void name() {
        spec.pathParam("first", "booking");

        RestfullData obj = new RestfullData();
        Map<String,Object> expectedData = obj.postMethod("John", "Doe", 11111,
                true,obj.bookindatesMethod("2021-09-09","2021-09-21"));

      Map<String,String>bookingdatesMap =obj.bookindatesMethod("2021-09-09","2021-09-21");
        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);

       assertEquals(expectedData.get("firstname"),((Map) actualData.get("booking")).get("firstname"));
       assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
       assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
       assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
       assertEquals(bookingdatesMap.get("checkin"),((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
       assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }
}