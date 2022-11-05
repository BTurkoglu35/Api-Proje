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

public class CalismaPutRequest extends JsonplaceholderBaseUrl {
    /*
    Given
      1)  https://jsonplaceholder.typicode.com/todos/200
      2)  {
           "userId": 60,
           "title": "I will succeed",
           "completed": false
           }
   When
    I send PUT Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 60,
           "title": "I will succeed",
           "completed": false
                               "id": 201
}
*/

    @Test
    public void test(){
        spec.pathParams("first","todos","second",200);

        JsonPlaceHolderTestData  obj= new JsonPlaceHolderTestData();
        Map<String,Object>expectedData=obj.expectedDataMethod(60,"I will succeed",false);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object>actualData=response.as(HashMap.class);

        assertEquals(expectedData.get("userid"), actualData.get("userid"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }

}
