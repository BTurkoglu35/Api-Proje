package put_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyPojo.DummyDataPojo;
import pojos.DummyPojo.DummyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class put02 extends DummyBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/update/21
         {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
    When
        user sends  PUT Request
     Then
            i) Status code is 200
            And
            ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
 */

    @Test
    public void put02() {
        spec.pathParams("first","update","second",21);

        DummyDataPojo data= new DummyDataPojo("Ali Can",111111,23,"Perfect image");
        DummyPojo expectedData= new DummyPojo("success",data,"Successfully! Record has been updated.");
        System.out.println(expectedData.toString());

        Response response=given().spec(spec).contentType(ContentType.JSON).body(data).when().put("/{first}/{second}");
        response.prettyPrint();

        DummyPojo actualData= ObjectMapperUtilities.convertJsonToJava(response.asString(), DummyPojo.class);
        System.out.println(actualData.toString());

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getStatus(), actualData.getStatus());

    }
}
