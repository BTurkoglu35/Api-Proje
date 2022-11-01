package Calisma;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalismaGet06 extends RestfulBaseUrl {
    /*
     Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
    {
  {
    "firstname": "Karst",
    "lastname": "Wismans",
    "totalprice": 452,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-08"
    },
    "additionalneeds": "Extra Pillow"
}
     */

    @Test
    public void name() {
        spec.pathParams("first", "booking", "second", 4243);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        System.out.println(response.getContentType());

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Karst"),
                        "lastname", equalTo("Wismans"),
                        "totalprice", equalTo(452),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2022-10-27"),
                        "bookingdates.checkout", equalTo("2022-11-08"),
                        "additionalneeds", equalTo("Extra Pillow"));


        JsonPath json = response.jsonPath();
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("Karst", json.getString("firstname"));
        assertEquals("Wismans", json.getString("lastname"));
        assertEquals(452, json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27", json.getString("bookingdates.checkin"));
        assertEquals("2022-11-08", json.getString("bookingdates.checkout"));
        assertEquals("Extra Pillow", json.getString("additionalneeds"));


    }
}
