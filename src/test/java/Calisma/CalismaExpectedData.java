package Calisma;

import bsaeUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyData;

import javax.xml.transform.sax.SAXResult;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaExpectedData extends DummyBaseUrl {

      /*
    Given
       https://dummy.restapiexample.com/api/v1/employee/3
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
   {
    "status": "success",
    "data": {
        "id": 3,
        "employee_name": "Ashton Cox",
        "employee_salary": 86000,
        "employee_age": 66,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
  }
 */

    @Test
    public void test01() {
        spec.pathParams("first", "v1", "second", "employee", "third", 3);

        //expected data
        DummyData obj = new DummyData();
        Map<String, Object> data = obj.dataMethod("Ashton Cox", "86000", 66);
        Map<String, String> expectedData = obj.expectedMethod("success", "Successfully! Record has been fetched.");

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        // response.jsonPath().prettyPrint();
        response.jsonPath().prettyPrint();
        JsonPath json= response.jsonPath();
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("status"), actualData.get("status"));
        assertEquals(data.get("data.name"), ((Map) actualData.get("data")).get("employee.name"));
        assertEquals(data.get("data.salary"), ((Map) actualData.get("data")).get("employee.salary"));
        assertEquals(data.get("data.age"), ((Map) actualData.get("data")).get("employee.age"));
        assertEquals(expectedData.get("message"), actualData.get("message"));


    }
}
