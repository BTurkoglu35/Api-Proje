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

public class CalismaPosttRequest extends JsonplaceholderBaseUrl {
/*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)
   When
       I send POST Request to the Url
   Then
       Status code is 201 {
            "userId": 65,
            "title": "trust yourself",
            "completed": true
                         }
   And
       response body is like {
                               "userId": 65,
                                "title": "trust yourself",
                               "completed": true
                               "id": 201
                               }
*/

    @Test
    public void name() {
        spec.pathParam("first","todos");

        JsonPlaceHolderTestData  obj= new JsonPlaceHolderTestData();
        Map<String,Object> expectedData=obj.expectedDataMethod(65,"trust yourself",true);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));




    }
}
