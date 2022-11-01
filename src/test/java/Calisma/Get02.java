package Calisma;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Get02 {
   /*
    Given
    https://restful-booker.herokuapp.com/booking/1
    When
    User send a GET Request to the url
    Then
    HTTP Status code should be 404
    And
    Status Line should be HTTP/1.1 404 Not Found
    And
    Response body contains "Not Found"
    And
    Response body does not contain "TechProEd"
    And
    Server is "Cowboy"
            */

 @Test
 public void get01() {
     String url="https://restful-booker.herokuapp.com/booking/1";

     Response response=given().when().get(url);
     response.prettyPrint();

   response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
   //body not found iceriyormu tsti yapiliyor
   assertTrue(response.asString().contains("Not Found"));
   //body TechProEd  icermiyormu tsti yapiliyor
   assertFalse(response.asString().contains("TechProEd"));
   //Server'in Cowboy olup olmadigini test ediyoruz
   assertEquals("Cowboy",response.getHeader("Server"));
 }
}
