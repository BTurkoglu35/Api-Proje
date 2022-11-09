package Utilities;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;


public class ObjectMapperUtilities {

    private static ObjectMapper mapper;
    //new ObjectMapper().readValue(jsonInString, HashMap .class);



    static {
        mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls){//Generic Method

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }
}
