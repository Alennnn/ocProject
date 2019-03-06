package com.online.college.common.util;


import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	
    private static  ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    public static String toJson(Object obj) throws IOException {
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
