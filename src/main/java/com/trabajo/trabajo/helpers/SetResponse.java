package com.trabajo.trabajo.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;



@Service
public class SetResponse {
    public Map<String, Object> setApiResponse(ApiResponse data) {
        Map<String, Object> response = new HashMap<>();

        response.put("ok", data.isOk());
        response.put("message", data.getMessage());
        response.put("data", data.getData());
        return response;

    }
    
}
