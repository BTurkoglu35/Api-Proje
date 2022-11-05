package Calisma;

import bsaeUrl.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestPojo.GorestDataPojo;
import pojos.GorestPojo.GorestMetaPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalısmaPojoGoRest extends GorestBaseUrl {
    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
   "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "active"
    }
     }

     */

    @Test
    public void name() {
        spec.pathParams("first","users","second",2986);

        //expected data
        GorestDataPojo dataPojo= new GorestDataPojo(2986,"Brijesh Kocchar","kocchar_brijesh@prosacco.com","female","active");
        GorestMetaPojo expectedData = new GorestMetaPojo(null,dataPojo);
        System.out.println(expectedData.toString());

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GorestMetaPojo actualData=response.as(GorestMetaPojo.class);
        System.out.println(actualData.toString());


        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getName(),actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(),actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());
        assertEquals(expectedData.getMeta(),actualData.getMeta());



    }
}
