package Calisma;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1-Postman'i manuel Api testleri icin kullandik.
    2-Otomasyon testleri icinde Rest Assured Library kullanacagiz.
    3-otomasyon testlerimizi yaparken asagidaki adimlari istiyoruz.
       A)Gereksinimleri anlamak,
       B)Test Case yaziyoruz
           1)Test case yaziminda gherkin dilini kullanacagiz.Bizler yazilim diline hakim olsakta karsimizdaki kisiler hakim olmayabilir.
           Ama gherkin ile yazilan testleri anlamada zorluk yasamyacaklardir
           Gherkin dilinde kullanacagimiz keywordler:
            - Given : on kosullar
            - When: yapilacak aksiyonlar (get(),put(),post(),delete(),patch()) icin kullanacagiz
            - Then: Istek yaptiktan sonra( request gonderdikten sonraki) dogrulama
            -And: Coklu islemlerde kullanacagiz

       C)Test kodlarini yazmaya baslayacagiz
           i)Set the URL,
           ii)Set the expected Data (beklenene datanin olusturulmasi,Pst,Put,Patch)
           iii)Type code to send request(Talep gondermek icin kod yazimi)
           iv)Do assertion (dogrulama yapmak)
            */

    /*
    Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){
      //  i)Set the URL,
        String url = "https://restful-booker.herokuapp.com/booking/101";
      //  ii)Set the expected Data (beklenene datanin olusturulmasi,Pst,Put,Patch)
          //bizden post,put yada patch istenmedgi icin bu casede kullanmayacagiz
     //   iii)Type code to send request(Talep gondermek icin kod yazimi)
       Response response= given().when().get(url);
       response.prettyPrint();

      //  iv)Do assertion (dogrulama yapmak)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

      //status  codu consola yazdiralim
        System.out.println("Status code : " + response.getStatusCode());

      //Content type konsola yazdiralim
        System.out.println("Content type : " + response.getContentType());

     //Status line'i konsola yazdiralim
        System.out.println("Status line : " + response.getStatusLine());

     //Header'i conslola yazdiarlim
        System.out.println("Header : " + response.getHeader("Server"));

     //Headers kismini konsola yazdiralim
        System.out.println("Headers : " + response.getHeaders());

     //Time konsola yazdiralim
        System.out.println("Time : " + response.getTime());

    }
}
