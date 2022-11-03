package test_data;

import java.util.HashMap;
import java.util.Map;

public class DummyData {

 public Map<String,Object> dataMethod( String name, String salary, Integer age){
     Map<String,Object> data = new HashMap<String,Object>();

     data.put("employee_name",name);
     data.put("employee_salary",salary);
     data.put("employee_age",age);

 return data;
 }

 public Map<String,String> expectedMethod(String status, String message){
     Map<String,String> expectedData = new HashMap<String,String>();
     expectedData.put("status",status);
     expectedData.put("message",message);
     return expectedData;

 }

}
