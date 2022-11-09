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
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Alistirma04 extends ReqresInBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void mapObjectMapper() {
        spec.pathParams("first","users","second",2);

        //expected data
        ReqresInUsersData obj= new ReqresInUsersData();
        Map<String,String>expectedData=obj.usersPostPutData("neo",null);

        //request and response

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");

        //do assertions

        Map<String,String> actualData= ObjectMapperUtilities.convertJsonToJava(response.asString(),Map.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));

    }

    @Test
    public void pojoObjectMapper() {

        spec.pathParams("first","users","second",2);

        //expectedData
        RequesUsersPojo expectedData= new RequesUsersPojo("neo");
        System.out.println(expectedData);

        //request and response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");

        //do assertions
        RequesUsersPojo actualData=ObjectMapperUtilities.convertJsonToJava(response.asString(),RequesUsersPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());

    }
}
