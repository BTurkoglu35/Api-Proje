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

public class Alistirma03 extends ReqresInBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void Map() {
        spec.pathParams("first","users","second",2);

        //expecteddata
        ReqresInUsersData obj= new ReqresInUsersData();
        Map<String,String>expectedData=obj.usersPostPutData("morpheus","zion president");


        //Request and response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do assertions
        Map<String,String>actualData=response.as(HashMap.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

    }

    @Test
    public void pojo() {
        spec.pathParams("first","users","second",2);

        //expectedData
        RequesUsersPojo expectedData= new RequesUsersPojo("morpheus","zion president");
        System.out.println(expectedData);

        //request and response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do assertions
        RequesUsersPojo actualData=response.as(RequesUsersPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());





    }
}
