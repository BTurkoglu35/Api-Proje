package delete_request;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {
        //set the url
        spec.pathParams("first","todos", "second",198);

        //expected data
        Map<String,String>expectedData=new HashMap<String,String>();

        //send the request and get the response
       Response response= given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //do assertions
       Map actualData=ObjectMapperUtilities.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData, actualData);

        //2.yol
        assertTrue(actualData.isEmpty());

        //3.yol
        assertEquals(0,actualData.size());
    }
}
