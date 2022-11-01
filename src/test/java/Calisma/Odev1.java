package Calisma;

import bsaeUrl.AutomationExBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev1 extends AutomationExBaseUrl {
    /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

    @Test
    public void odev1() {

        spec.pathParam("first", "productsList");

        Response response = given().spec(spec).when().get("/{first}");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertEquals("text/html; charset=utf-8", response.getContentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());

        List<String> usertype = jsonPath.getList("products.category.usertype.usertype");

        assertEquals(12, usertype.stream().filter(t -> t.equals("Women")).count());
        assertEquals(9, usertype.stream().filter(t -> t.equals("Men")).count());
        assertEquals(13, usertype.stream().filter(t -> t.equals("Kids")).count());

    }
}

