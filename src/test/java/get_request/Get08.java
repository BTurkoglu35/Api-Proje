package get_request;

import bsaeUrl.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
*/
    @Test
    public void test() {

        //set the url
        spec.pathParams("first", "todos", "second", 2);
        //expected data=>Payload
        //set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //do assertions
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.getHeader("Via"));
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(200, response.statusCode());

    }

    //dinamik yontem
    @Test
    public void testb() {

        //set the url
        spec.pathParams("first", "todos", "second", 2);
        //expected data=>Payload
        //set the expected data
        JsonPlaceHolderTestData objJsonPlaceHolder = new JsonPlaceHolderTestData();
       Map<String,Object>expectedData= objJsonPlaceHolder.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //do assertions
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.getHeader("Via"));
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(200, response.statusCode());

    }
    //De-serializotion :Json datayi Java objesine cevirme
    //Serialization : Java objesini json formatina cevirme
    //De-serializotion'i iki turlu yapacagiz.
    //Gson:Google tarafindan uretilmistir
    //Object Mapper
}
