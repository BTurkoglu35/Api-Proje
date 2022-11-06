package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresInUsersData {

    public Map<String,String>dataMethod(String email,String first_name,String last_name,String avatar){
        Map<String,String >dataMap = new HashMap<String,String>();
        dataMap.put("email",email);
        dataMap.put("first_name",first_name);
        dataMap.put("last_name",last_name);
        dataMap.put("avatar",avatar);
        return dataMap;
    }
    public Map<String,String>supportMethod (String url,String text){
        Map<String,String>supportMap = new HashMap<String,String>();
        supportMap.put("url",url);
        supportMap.put("text",text);
        return supportMap;
    }

    public Map<String,String> usersPostPutData(String name, String job){
        Map<String,String>usersMap = new HashMap<String,String>();
        if(name != null){
        usersMap.put("name",name);}
        if (job != null){
            usersMap.put("job",job);
        }

        return usersMap;
    }

    public String expectedDataInString(String name, String job){//Dinamik expected data methodu: Json datayı String bir container olarak return ediyor.
        String expectedData = " {\n" +
                "                 \"name\": "+name+",\n" +
                "                 \"job\": "+job+"\n" +
                "               }";



        return expectedData;
    }

}
