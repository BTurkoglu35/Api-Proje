package Calisma;

import bsaeUrl.AutomationExBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Alistirma01 extends AutomationExBaseUrl {
     /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of  Polo brands more than H&M
*/


    @Test
    public void test01() {
        RequestSpecification requestSpecification = spec.pathParam("first", "brandsList");

        Response response =given().spec(spec).when().get("/{first}");
        response.jsonPath().prettyPrint();

        List<String>hm= response.jsonPath().getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println(hm.size());
        List<String>polo=response.jsonPath().getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println(polo.size());

        assertEquals(200,response.getStatusCode());
        assertEquals("text/html; charset=utf-8", response.getContentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());
        assertTrue(hm.size()<polo.size());








    }
}
