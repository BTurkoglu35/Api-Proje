package Utilities;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;


public class ObjectMapperUtilities {

    private static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    public static <T> T convertJsonToJava(String json,Class<T> type) {//generic method
      T javaResult=null;
        try {
         javaResult=mapper.readValue(json,type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     return javaResult;
    }
}
