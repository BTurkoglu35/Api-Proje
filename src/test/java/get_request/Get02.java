package get_request;

import bsaeUrl.RequseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02 extends RequseBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    @Test
    public void test() {
        //set th url
        spec.pathParams("first", "users", "second", 23);

        //send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertions
        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("cloudflare",response.getHeader("Server"));
        System.out.println(response.body().asString().replaceAll("\\s","").length());
        assertEquals(2, response.body().asString().replaceAll("\\s",""));

    }


}
