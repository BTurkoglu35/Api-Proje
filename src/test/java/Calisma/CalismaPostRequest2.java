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

public class CalismaPostRequest2 extends ReqresInBaseUrl {
    /*
    Given
      1) https://reqres.in/api/users
      2)
   When
       I send POST Request to the Url
   Then
       Status code is 201 {
            {
    "name": "Nazli",
    "job": "Tester",
   And
       response body is like {
                               "name": "Nazli",
                                "job": "Tester"
*/

    @Test
    public void name() {
        spec.pathParam("first","users");

        ReqresInUsersData obj= new ReqresInUsersData();
        Map<String,String>expectedData=obj.usersPostPutData("nazli","leader");

        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,String>actualData=response.as(HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
    }
}
