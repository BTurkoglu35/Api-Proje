package Calisma;

import bsaeUrl.ReqresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaJsonList extends ReqresInBaseUrl {
      /*
  Given
         https://reqres.in/api/users
  When
       I send GET Request to the URL
  Then

       1)Status code is 200
       2)Print all first_name
       3)Print all ids greater than 4 on the console
         Assert that there are 2 ids greater than 4
       4)Print all lastname whose ids are less than 4 on the console
         Assert that the number of names whose ids are less than 4 is 3
*/

    @Test
    public void test01() {
        spec.pathParam("first", "users");

        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        JsonPath json = response.jsonPath();

        //Print all first_name
        List<String> firstname = json.getList("data.first_name");
        firstname.forEach(System.out::println);

        // 3)Print all ids greater than 4 on the console
        // Assert that there are 2 ids greater than 4
        List<Integer> id = json.getList("data.findAll{it.id>4}.id");
        assertEquals(2, id.size());

        // 4)Print all lastname whose ids are less than 4 on the console
        //  Assert that the number of names whose ids are less than 4 is 3
        List<String> lastname = json.getList("data.findAll{it.id<4}.last_name");
        assertEquals(3, lastname.size());


    }
}
