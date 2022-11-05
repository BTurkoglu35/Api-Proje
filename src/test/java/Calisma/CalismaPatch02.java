package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresInUsersData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaPatch02 extends ReqresInBaseUrl {
    /*
    Given
     1) https://reqres.in/api/users/2
        {
    "name": "mucellas",


}
    When
    I send PATCH Request to the Url
            Then
    Status code is 200
    And response body is like   {
                {
    "name": "mucellas",
    "job": "zion resident",
  }
 */

    @Test
    public void name() {
        spec.pathParams("first", "users", "second", 2);

        ReqresInUsersData obj = new ReqresInUsersData();
        Map<String, String> expectedData = obj.usersPostPutData("mucellas", null);
        System.out.println(expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();
        Map<String, String>actualData=response.as(HashMap.class);
        System.out.println(actualData);


        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));




    }
}
