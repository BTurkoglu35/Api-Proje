package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RequesPojo.RequesInDataPojo;
import pojos.RequesPojo.RequesInSupportPojo;
import pojos.RequesPojo.RequesUnkownPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaPojoRequesIn extends ReqresInBaseUrl {
      /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void name() {
        spec.pathParams("first","unknown","second",3);

        //expectedData
        RequesInDataPojo data= new RequesInDataPojo(3,"true red",2002,"#BF1932","19-1664");
        RequesInSupportPojo support= new RequesInSupportPojo("https://reqres.in/#support-heading","To keep ReqRes free, contributions towards server costs are appreciated!");
        RequesUnkownPojo expectedData= new RequesUnkownPojo(data,support);
        System.out.println(expectedData.toString());

        //set the request
        Response response =given().spec(spec).when().get("/{first}/{second}");

        RequesUnkownPojo actualData=response.as(RequesUnkownPojo.class);
        System.out.println(actualData.toString());

        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getName(), actualData.getData().getName());
        assertEquals(expectedData.getData().getYear(), actualData.getData().getYear());
        assertEquals(expectedData.getData().getColor(), actualData.getData().getColor());
        assertEquals(expectedData.getData().getPantone_value(), actualData.getData().getPantone_value());
        assertEquals(expectedData.getSupport().getUrl(), actualData.getSupport().getUrl());
        assertEquals(expectedData.getSupport().getText(), actualData.getSupport().getText());




    }
}
