package get_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyPojo.DummyDataPojo;
import pojos.DummyPojo.DummyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyBaseUrl {
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get17() {
       spec.pathParams("first","employee","second",1);

        DummyDataPojo data= new DummyDataPojo("Tiger Nixon",320800,61,"");
        DummyPojo expectedData= new DummyPojo("success",data,"Successfully! Record has been fetched.");
        System.out.println(expectedData.toString());

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        DummyPojo actualData= ObjectMapperUtilities.convertJsonToJava(response.asString(), DummyPojo.class);
        System.out.println(actualData.toString());

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
    }
}
