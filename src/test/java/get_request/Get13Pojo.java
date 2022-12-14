package get_request;

import bsaeUrl.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestPojo.GorestDataPojo;
import pojos.GorestPojo.GorestMetaPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GorestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void name() {
        spec.pathParams("first","users","second",2508);

        GorestDataPojo data = new GorestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name","female","active");
        GorestMetaPojo expectedData= new GorestMetaPojo(null,data);


        Response response= given().spec(spec).when().get("/{first}/{second}");

        GorestMetaPojo actualData= response.as(GorestMetaPojo.class);


        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getName(), actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(), actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(), actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(), actualData.getData().getStatus());
        assertEquals(expectedData.getMeta(), actualData.getMeta());


     }
}
