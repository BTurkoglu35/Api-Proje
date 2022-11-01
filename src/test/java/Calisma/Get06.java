package Calisma;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends RestfulBaseUrl {
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
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void name() {
        spec.pathParams("first","booking","second","2325");

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2022-10-27"),
                        "bookingdates.checkout",equalTo("2022-11-07"),
                        "additionalneeds",equalTo("None"));

        //2.yol

        JsonPath json= response.jsonPath();

        assertEquals("Bradley",json.getString("firstname"));
        assertEquals("Pearson",json.getString("lastname"));
        assertEquals(132,json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
        assertEquals("None",json.getString("additionalneeds"));

        //3.yol
        //soft assert classsi 3 adimda  kullanilir

        //i) Obje olusturma
        SoftAssert softAssert=new SoftAssert();

        //ii) Do assertions (dogrulama yapma )
        softAssert.assertEquals(json.getString("firstname"),"Bradley","First name hatali");
        softAssert.assertEquals(json.getString("lastname"),"Pearson","lastname hatali");
        softAssert.assertEquals(json.getInt("totalprice"),132,"total price hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false,"depositpaidhatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27","bookingdates.checkin hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07","bookingdates.checkout hatali");
        softAssert.assertEquals(json.getString("additionalneeds"),"None","additionalneeds hatali");

        //iii)Assertions close
        softAssert.assertAll();

        //Eger islemin sonunda softAssert.assertAll(); kullanmazsak testlerimzde hata olsa bile testlerimiz gecti gozukur.

















    }
}
