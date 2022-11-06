package get_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get14ObjectMapper extends JsonplaceholderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void name() {
        spec.pathParams("first","todos","second",198);


        String expectedDataInString=new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
        Map expectedData= ObjectMapperUtilities.convertJsonToJava(expectedDataInString,Map.class);
        System.out.println(expectedData);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map actualData=ObjectMapperUtilities.convertJsonToJava(response.asString(),Map.class);
        System.out.println(actualData);

     assertEquals(200,response.getStatusCode());
     assertEquals(expectedData.get("userId"),actualData.get("userId"));
     assertEquals(expectedData.get("title"),actualData.get("title"));
     assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}
