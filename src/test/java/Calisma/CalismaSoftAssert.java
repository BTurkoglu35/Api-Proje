package Calisma;

import bsaeUrl.RequseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import org.testng.asserts.SoftAssert;
import test_data.ReqresInUnkownData;
import test_data.ReqresInUsersData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class CalismaSoftAssert extends RequseBaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
    },
    "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
      */

    @Test
    public void test01() {

        spec.pathParams("first","unknown","second",3);

        ReqresInUnkownData obj = new ReqresInUnkownData();
        Map<String,Object> dataExpected =obj.dataMethod(3,"true red",2002,"#BF1932","19-1664");
        Map<String,String>supportExpected =obj.supportMethod("https://reqres.in/#support-heading","To keep ReqRes free, contributions towards server costs are appreciated!");

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        Map<String,Object> actualData=response.as(HashMap.class);


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(((Map) actualData.get("data")).get("id"),dataExpected.get("id"));
        softAssert.assertEquals(((Map) actualData.get("data")).get("name"),dataExpected.get("name"));
        softAssert.assertEquals(((Map) actualData.get("data")).get("year"),dataExpected.get("year"));
        softAssert.assertEquals(((Map) actualData.get("data")).get("color"),dataExpected.get("color"));
        softAssert.assertEquals(((Map) actualData.get("data")).get("pantone_value"),dataExpected.get("pantone_value"));
        softAssert.assertEquals(((Map) actualData.get("support")).get("url"),supportExpected.get("url"));
        softAssert.assertEquals(((Map) actualData.get("support")).get("text"),supportExpected.get("text"));



















    }


}
