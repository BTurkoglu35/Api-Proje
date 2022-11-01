package get_request;

import bsaeUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get04 extends RestfulBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */

    @Test
    public void get04() {

        //set th url
        spec.pathParams("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");
        //set the expected data

        //send the request aand get the response
       Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //do assertions
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}
