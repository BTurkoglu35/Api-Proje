package Calisma;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
 */

    @Test
    public void name() {
        spec.pathParams("first","booking").queryParams("firstname","Javier","lastname","Sevilla");

        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        assertEquals(200,response.getStatusCode());
       assertTrue(response.asString().contains("bookingid"));



    }
}
