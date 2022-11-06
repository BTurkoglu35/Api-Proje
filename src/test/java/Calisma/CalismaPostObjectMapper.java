package Calisma;


import Utilities.ObjectMapperUtilities;
import bsaeUrl.ReqresInBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.RequesPojo.RequesUsersPojo;
import test_data.ReqresInUsersData;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaPostObjectMapper extends ReqresInBaseUrl {
     /*
    Given
      1) https://reqres.in/api/users
      2)
   When
       I send POST Request to the Url
   Then
       Status code is 201 {
           {
    "name": "ali",
    "job": "QA"
}
   And
       response body is like {
    "name": "ali",
    "job": "QA"
}
*/

    @Test
    public void objectMapper() throws IOException {

        spec.pathParam("first","users");

        RequesUsersPojo expectedData= new RequesUsersPojo("ali","QA");
        System.out.println(expectedData.toString());

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        RequesUsersPojo actualData= ObjectMapperUtilities.convertJsonToJava(response.asString(),RequesUsersPojo.class);
        System.out.println(actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());


    }
}
