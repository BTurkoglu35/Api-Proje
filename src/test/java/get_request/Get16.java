package get_request;

import bsaeUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.ast.InterfaceHelperClassNode;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
    Given
         URL: https://dummy.restapiexample.com/api/v1/employees
   When
         User sends get request
   Then
     i) Status code is 200
    And
        ii) There are 24 employees
    And
       iii) "c" and "Garrett Winters" are among the employees
    And
      iv) The greatest age is 66
    And
      v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
      vi) Total salary of all employees is 6,644,770

     */

    @Test
    public void get16(){
        spec.pathParam("first","employees");

       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       response.then().assertThat().body("data.id",hasSize(24),
               "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ages=jsonPath.getList("data.employee_age");
        Collections.sort(ages);
        assertEquals(66,(int)ages.get(ages.size()-1));

        //The name of the lowest age is "Tatyana Fitzpatrick"
       String lowestAgeEmployee= jsonPath.getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");

        assertEquals("[Tatyana Fitzpatrick]",lowestAgeEmployee);

        //Total salary of all employees is 6644770
        List<Integer>salaries=response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = "+salaries);

        //1.yol
        int sum =0;

        for(int w:salaries){
            sum+=w;

        }
        System.out.println("sum ="+sum);
        assertEquals(6644770,sum);

        //2.yol
        salaries.stream().reduce(0,Integer::sum);

        //3.yol
        int sum3=salaries.stream().reduce(0,Math::addExact);
        System.out.println("sum3 = "+sum3);


    }


}

