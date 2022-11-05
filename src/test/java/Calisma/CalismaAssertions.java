package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalismaAssertions extends ReqresInBaseUrl {
    /*
    Given
       https://reqres.in/api/users?page=1
    When
        user send GET request
    Then
         The value of "total" is 12
      And
        The id=3  "avatar" value of  should be "https://reqres.in/img/faces/3-image.jpg"
      And
         The number of emails should be 6
      And
         "Emma" and Eve are among the users
     */

    @Test
    public void name() {

       String url="https://reqres.in/api/users?page=1";
       Response response =given().when().get(url);

        response.prettyPrint();

        JsonPath jsonPath =response.jsonPath();

        assertEquals(12,jsonPath.getInt("total"));
        assertEquals("[https://reqres.in/img/faces/3-image.jpg]",jsonPath.getString("data.findAll{it.id==3}.avatar"));
        assertTrue(jsonPath.getList("data.email").size()==6);
        assertTrue(jsonPath.getList("data.first_name").contains("Emma"));
        assertTrue(jsonPath.getList("data.first_name").contains("Eve"));

    }
}
