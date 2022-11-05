package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfullData {

    public Map<String,String> bookindatesMethod(String checkin,String checkout){
        Map<String,String>bookingdates = new HashMap<String,String>();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);
        return bookingdates;
    }

    public Map<String,Object> dataMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,String additionalneeds){
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("firstname",firstname);
        dataMap.put("lastname",lastname);
        dataMap.put("totalprice",totalprice);
        dataMap.put("depositpaid",depositpaid);
        dataMap.put("additionalneeds",additionalneeds);

        return dataMap;
    }

    public Map<String,Object> postMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String>bookingdates){
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("firstname",firstname);
        dataMap.put("lastname",lastname);
        dataMap.put("totalprice",totalprice);
        dataMap.put("depositpaid",depositpaid);
        dataMap.put("bookingdates",bookingdates);

        return dataMap;
    }


}
