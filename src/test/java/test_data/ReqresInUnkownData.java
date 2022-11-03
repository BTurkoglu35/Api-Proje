package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresInUnkownData {

     public Map<String,Object> dataMethod(Integer id,String name,Integer year,String color,String pantone_value) {
          Map<String,Object> data = new HashMap<String,Object>();
          data.put("id",id);
          data.put("name",name);
          data.put("year",year);
          data.put("color",color);
          data.put("pantone_value",pantone_value);
          return data;
     }
     public Map<String,String> supportMethod(String url,String text){
          Map<String,String> support = new HashMap<String,String>();
          support.put("url",url);
          support.put("text",text);
          return support;
     }

}
