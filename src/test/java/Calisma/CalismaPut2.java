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

public class CalismaPut2 extends ReqresInBaseUrl {
    /*
       1) Given
    https://reqres.in//api/users/2

   When
    I send PUT Request to the Url

    "name": "morpheus",
    "job": "zion resident"

   Then
       Status code is 201
   And
       response body is like {
                          {
    "name": "morpheus",
    "job": "zion resident",

}
*/

    @Test
    public void name() {
        spec.pathParams("first","users","second",2);

        //expected data
        ReqresInUsersData obj= new ReqresInUsersData();
       Map<String,String> expectedData= obj.usersPostPutData("morpheus","zion resident");

     Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");

     Map<String,String>actualData=response.as(HashMap.class);

     assertEquals(expectedData.get("name"),actualData.get("name"));
     assertEquals(expectedData.get("job"),actualData.get("job"));








    }
}
