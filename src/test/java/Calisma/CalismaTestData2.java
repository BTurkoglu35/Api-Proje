package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresInUsersData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaTestData2 extends ReqresInBaseUrl {
      /*
    Given
       https://reqres.in/api/users/2
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {

    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
*/
    @Test
    public void test(){
        //set th url
        spec.pathParams("first","users","second",2);

        //set the expected data
        ReqresInUsersData obj= new ReqresInUsersData();
        Map<String,String> data=obj.dataMethod("janet.weaver@reqres.in","Janet","Weaver","https://reqres.in/img/faces/2-image.jpg");
        Map<String,String>support=obj.supportMethod("https://reqres.in/#support-heading","To keep ReqRes free, contributions towards server costs are appreciated!");

        //set the request
        Response response=given().spec(spec).when().get("/{first}/{second}");
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println(actualData);

        //do assertions
        assertEquals(data.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(data.get("first_name"),((Map)actualData.get("data")).get("first_name"));
        assertEquals(data.get("last_name"),((Map)actualData.get("data")).get("last_name"));
        assertEquals(data.get("avatar"),((Map)actualData.get("data")).get("avatar"));
        assertEquals(support.get("url"),((Map)actualData.get("support")).get("url"));
        assertEquals(support.get("text"),((Map)actualData.get("support")).get("text"));

    }
     /*
    Given
       https://reqres.in/api/users/2
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {

    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
*/

    @Test
    public void name() {
        spec.pathParams("first","users","second",2);

        Response response= given().spec(spec).when().get("/{first}/{second}");

        JsonPath json= response.jsonPath();

        assertEquals("janet.weaver@reqres.in",json.getString("data.email"));
        assertEquals("Janet",json.getString("data.first_name"));
        assertEquals("Weaver",json.getString("data.last_name"));
        assertEquals("https://reqres.in/img/faces/2-image.jpg",json.getString("data.avatar"));
        assertEquals("https://reqres.in/#support-heading",json.getString("support.url"));
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",json.getString("support.text"));

    }
}
