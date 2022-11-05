package get_request;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.restfulPojo.BookingPojo;
import pojos.restfulPojo.BookingdatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12PojoClass extends RestfulBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                        "firstname": "Dane",
    "lastname": "Combs",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
  */

    @Test
    public void get12Pojo() {
        spec.pathParams("first","booking","second",18);

        //set the expected data
        BookingdatesPojo  bookingdates = new BookingdatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Dane","Combs",111,true,bookingdates,"Breakfast");
        System.out.println(expectedData.toString());

        //send the Reuest and get the response
       Response response= given().spec(spec).when().get("/{first}/{second}");

       //Do assertions
        BookingPojo actualData=response.as(BookingPojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
