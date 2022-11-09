package Calisma;

import Utilities.ObjectMapperUtilities;
import bsaeUrl.PetstoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetsPojoExpectedDataPojo;
import pojos.PetstorePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Alistirma05 extends PetstoreBaseUrl {
       /*
    https://petstore.swagger.io/v2 documantation adresini kullanarak 'user' olusturan
    ve olusan user'i silen bir otomasyon kodu yazınız.

   {
 "id": 12,
  "username": "Asus",
  "firstName": "Sude",
  "lastName": "Test",
  "email": "asus@gmail.com",
  "password": "2020",
  "phone": "2020",
  "userStatus": 0
}

post response
{
    "code": 200,
    "type": "unknown",
    "message": "12"
}

  Delete response
    "code": 200,
    "type": "unknown",
    "message": "Asus"
    */

    @Test
    public void name() {
        spec.pathParam("first","user");

        PetstorePojo data= new PetstorePojo(12,"Asus","Sude","Test","asus@gmail.com","2020","2020",0);
        PetsPojoExpectedDataPojo expectedData= new PetsPojoExpectedDataPojo(200,"unknown","12");

        Response response= given().spec(spec).contentType(ContentType.JSON).body(data).when().post("/{first}");
        response.prettyPrint();

        PetsPojoExpectedDataPojo actualData= ObjectMapperUtilities.convertJsonToJava(response.asString(), PetsPojoExpectedDataPojo.class);

       assertEquals(200,response.getStatusCode());
       assertEquals(expectedData.getCode(), actualData.getCode());
       assertEquals(expectedData.getType(), actualData.getType());
       assertEquals(expectedData.getMessage(), actualData.getMessage());

    }

    @Test
    public void deleteTest() {
        spec.pathParams("first","user", "second","Asus");

        PetsPojoExpectedDataPojo expectedData= new PetsPojoExpectedDataPojo(200,"unknown","Asus");

        Response response= given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        PetsPojoExpectedDataPojo actualData = ObjectMapperUtilities.convertJsonToJava(response.asString(), PetsPojoExpectedDataPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getCode(),actualData.getCode());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getType(),actualData.getType());

    }
}
