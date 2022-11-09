package Calisma;

import bsaeUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;
import pojos.DummyPojo.DummyDataPojo;
import pojos.DummyPojo.DummyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalismaPojoDummy extends DummyBaseUrl {

     /*
   Given
         https://dummy.restapiexample.com/api/v1/employee/1
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
   {
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}

     */

    @Test
    public void name() {
        spec.pathParams("first","employee","second",1);

        DummyDataPojo data= new DummyDataPojo("Tiger Nixon",320800,61,"");
        DummyPojo expectedData= new DummyPojo("success",data,"Successfully! Record has been fetched.");
        System.out.println(expectedData.toString());


        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        DummyPojo actualData=response.as(DummyPojo.class);
        System.out.println(actualData.toString());

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());

        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(actualData.getMessage(),actualData.getMessage());

    }
}
