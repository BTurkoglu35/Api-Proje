package get_request;

import bsaeUrl.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GorestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Fr"Jahnu Khan","Malti Verma","Daevika Bhat" are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void test(){
        spec.pathParams("first","users");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //MATTCHERS
        response.then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),
                "data.status",hasItem("active"),
                "data.name",hasItems("Jahnu Khan","Malti Verma","Daevika Bhat"));

        //1.yol
        List<String> gender=response.jsonPath().getList("data.gender");
        long male =gender.stream().filter(t->t.equals("male")).count();
        long female =gender.stream().filter(t->t.equals("female")).count();
        assertTrue(male>=female);

        //2.yol kadin ve erkek sayilarini grovy ile bulalim
    List<String>femaleNames= response.jsonPath().getList("data.findAll{it.gender=='female'}.gender");
    List<String>maleNames= response.jsonPath().getList("data.findAll{it.gender=='male'}.gender");
    assertTrue(maleNames.size()>=femaleNames.size());

        //JSON
        JsonPath jsonPath = response.jsonPath();

        assertEquals(10, jsonPath.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1",jsonPath.getString("meta.pagination.links.current"));
        assertEquals(10, jsonPath.getList("data").size());
        List<String>status= jsonPath.getList("data.status");
        int active= (int) status.stream().filter(t->t.equals("active")).count();
        assertTrue(active>1);



    }

}
