package Calisma;

import bsaeUrl.RequseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaJson extends RequseBaseUrl {
     /*
Given
    https://reqres.in/api/users
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "application/json; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
    Print all ids greater than 3 on the console
    Assert that there are 3 ids greater than 3
 And
    Print all names whose ids are less than 3 on the console
      Assert that the number of names whose ids are less than 3 is 2

  */

    @Test
    public void name() {
        spec.pathParams("first","users");

        Response response= given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        JsonPath json= response.jsonPath();
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());

        //  Print all ids greater than 3 on the console
        //    Assert that there are 3 ids greater than 3
        List<Integer> id=json.getList("data.findAll{it.id>3}.id");
        assertEquals(3, id.size());
        // wPrint all nameshose ids are less than 3 on the console
        //Assert that the number of names whose ids are less than 3 is 2
       List<Integer>ids=json.getList("data.findAll{it.id<3}.name");
       assertEquals(2,ids.size());

    }
}
