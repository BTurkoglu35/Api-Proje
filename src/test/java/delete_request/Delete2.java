package delete_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.DummyPojo.DummyDeletePojo;
import pojos.DummyPojo.DummyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete2 extends DummyBaseUrl {
/*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */

     /*
     Given
        URL: https://dummy.restapiexample.com/api/v1/delete/2
     When
        User sends delete request and get rasponse
     Then
        Assert:     i) Status code is 200
     And
       ii) "status" is "success"
     And
       iii) "data" is "2"
     And
       iv) "message" is "Successfully! Record has been deleted"
      */

    @Test
    public void delete02() {
        spec.pathParams("first","delete","second",23);

        DummyDeletePojo expectedData = new DummyDeletePojo();
        System.out.println(expectedData);
        Response response =given().spec(spec).when().delete("/{first}/{second}");

        DummyDeletePojo actualData = ObjectMapperUtilities.convertJsonToJava(response.asString(), DummyDeletePojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getStatus(), actualData.getStatus());






    }
}
