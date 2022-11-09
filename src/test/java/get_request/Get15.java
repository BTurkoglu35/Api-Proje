package get_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.restfulPojo.BookingPojo;
import pojos.restfulPojo.BookingdatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {"firstname": "Guoqiang",
    "lastname": "Morante Briones",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2018-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get15() {

        spec.pathParams("first", "booking", "second", 22);

        BookingdatesPojo bookingDatesPojo = new BookingdatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Guoqiang","Morante Briones",111,true,bookingDatesPojo,"Breakfast");
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = ObjectMapperUtilities.convertJsonToJava(response.asString(), BookingPojo.class);


        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


        //soft assertions
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());

        softAssert.assertAll();
    }
}
