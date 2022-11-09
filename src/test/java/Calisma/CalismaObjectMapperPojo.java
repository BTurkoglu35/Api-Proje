package Calisma;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CalismaObjectMapperPojo extends JsonplaceholderBaseUrl {
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
    public void pojo() {
        //expected data ve actual data yi pojo class yardimi ile olusturacagiz

        //1 set the url
        spec.pathParams("first","todos","second",198);

        //expected data
        //pojo classda constructor oldugu icin verileri constructara girmeliyiz
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo", true);
        System.out.println(expectedData.toString());// pojoda olusturdugumuz toString methodu yardimi ile yazdiriyoruz

        //request and response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().get("/{first}/{second}");

        //do assertions
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);//Response'u  JsonPlaceHolderPojo donusturerek data type JsonPlaceHolderPojo olan bir conteynara attik
        System.out.println(actualData.toString());

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
    }

    @Test
    public void objectMapper() {
        //expected data ve actaul datayi object mapper ile olusturacagiz
        spec.pathParams("first","todos","second",198);

       //expected data
        // JsonPlaceHolderTestData classinda olusturdugumuz string return eden method ile expected datalari giridk. Daha sonra gelen
        //  ObjectMapperUtilities classindaki convertJsonToJava methodu ile olusturdugumuz String variable'i map'e cevirdik
        //Ve data typi Map olan expected data'nin icerisine atarak expected datayi olusutduk.
        String expectedDataInString=new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
        Map expectedData= ObjectMapperUtilities.convertJsonToJava(expectedDataInString,Map.class);
        System.out.println(expectedData);

        //request and response
        Response response=given().spec(spec).when().get("/{first}/{second}");


        //do assertions
        //response'u asString methodu ile Stringe cevirerek  ObjectMapperUtilities.convertJsonToJava methodu yardimi ile map'e cevirdik ve map data type'i olustrdugumuz actualData'ya koyduk
        Map actualData=ObjectMapperUtilities.convertJsonToJava(response.asString(),Map.class);
        System.out.println(actualData);

        //expected data ve actual datayi object mapper yardimi ile map seklinde olustrudugumuz icin dogrulama yapabilriz
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }

    @Test
    public void pofoVeObjectMapper() {
        //expected data =pojo ve actual data = objectMapper ile olusturuyoruz
        spec.pathParams("first","todos","second",198);

        //expectedData
        //expectedDatayi pojo class ile olusturduk
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        System.out.println(expectedData.toString());

        //request and response
        Response response =given().spec(spec).when().get("/{first}/{second}");


        //do assertions
        //actualDatanin data type'i JsonPlaceHolderPojo olacak sekilde olusturuyoruz.Cunku expected data ile ayni olmak zorunda.Assertions yapmak icin
        // responsu asString() methodu ile Stringe cevirdikten sonra elimizdeki Stringi ObjectMapperUtilities.convertJsonToJava methodu ile JsonPlaceHolderPojo ceviriyoruz
        //artik actual ve expected ayni data type sahip oldugu icin assertions yapabilriz

        JsonPlaceHolderPojo actualData =ObjectMapperUtilities.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}
