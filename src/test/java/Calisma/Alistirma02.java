package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RequesPojo.RequesUsersPojo;
import test_data.ReqresInUsersData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Alistirma02 extends ReqresInBaseUrl {
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void MapTest(){
        spec.pathParam("first","users");

        //expectedData
        ReqresInUsersData obj= new ReqresInUsersData();
        Map<String,String> expectedData=obj.usersPostPutData("morpheus", "leader");
        System.out.println(expectedData);

        ///request and response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertions
        Map<String,String> actualData=response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
    }

    @Test
    public void pojoTest(){
        spec.pathParam("first","users");

        //expectedData
        RequesUsersPojo expectedData=new RequesUsersPojo("morpheus","leader");
        System.out.println(expectedData);

        //Request and response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertions
        RequesUsersPojo actualData=response.as(RequesUsersPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());



    }
}
