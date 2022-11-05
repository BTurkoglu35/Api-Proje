package Calisma;

import bsaeUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaPatch01  extends JsonplaceholderBaseUrl {
    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
            2) {
          "completed": false,
    }
    When
    I send PATCH Request to the Url
            Then
    Status code is 200
    And response body is like   {
                  "userId": 10,
                "title": "Wash the dishes",
                "completed": false,
                "id": 198
    }
 */

    @Test
    public void name() {

        spec.pathParams("first","todos","second",198);

        //expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData=obj.expectedDataMethod(null,null,false);

        //set the request
        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);

        //do assertions
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
