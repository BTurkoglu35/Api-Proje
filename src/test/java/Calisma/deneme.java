package Calisma;

import Utilities.ObjectMapperUtilities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetsPojoExpectedDataPojo;
import pojos.PetstorePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class deneme {

    //5:
    /*
    https://petstore.swagger.io/v2 documantation adresini kullanarak 'user' olusturan
    ve olusan user'i silen bir otomasyon kodu yazınız.

   {
 "id": 432,
  "username": "Javacan",
  "firstName": "Murtaza",
  "lastName": "Can",
  "email": "Mc@gmail.com",
  "password": "qwerty123",
  "phone": "02342343",
  "userStatus": 0
}

post response
{
    "code": 200,
    "type": "unknown",
    "message": "199"
}

  Delete response
    "code": 200,
    "type": "unknown",
    "message": "Javacan"
    */

    @Test
    public void e05() {

        // post
        String url = "https://petstore.swagger.io/v2/user/";

        PetstorePojo postData = new PetstorePojo(199, "Javacan", "Murtaza", "Can", "Mc@gmail.com", "qwerty123", "02342343", 0);
        PetsPojoExpectedDataPojo expectedData = new PetsPojoExpectedDataPojo(200, "unknown", "199");
        System.out.println("expectedData = " + expectedData);


        Response response = given().contentType(ContentType.JSON).body(postData).when().post(url);
        response.prettyPrint();


        PetsPojoExpectedDataPojo actualData = ObjectMapperUtilities.convertJsonToJava(response.asString(), PetsPojoExpectedDataPojo.class);
        System.out.println("actualData = " + actualData);


        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getCode(), actualData.getCode());
        assertEquals(expectedData.getType(), actualData.getType());
        assertEquals(expectedData.getMessage(), actualData.getMessage());

        // get
        String url1 = "https://petstore.swagger.io/v2/user/Javacan";

        Response responseGet = given().when().get(url1);
        responseGet.prettyPrint();



        // delete
        String url2 = "https://petstore.swagger.io/v2/user/Javacan";


        PetsPojoExpectedDataPojo expectedDataDelete = new PetsPojoExpectedDataPojo(200, "unknown", "Javacan");
        System.out.println("expectedDataDelete = " + expectedDataDelete);

        Response response1 = given().contentType(ContentType.JSON).when().delete(url2);
        response1.prettyPrint();

        PetsPojoExpectedDataPojo actualDataDelete = ObjectMapperUtilities.convertJsonToJava(response1.asString(), PetsPojoExpectedDataPojo.class);
        System.out.println("actualDataDelete = " + actualDataDelete);

        assertEquals(expectedDataDelete.getCode(),actualDataDelete.getCode());
        assertEquals(expectedDataDelete.getType(),actualDataDelete.getType());
        assertEquals(expectedDataDelete.getMessage(),actualDataDelete.getMessage());
    }}