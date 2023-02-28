package com.trabajo.trabajo.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;




@Service
public class ValidacionCampos {
    @Autowired
    private SetResponse  setApiResponse;



    public ResponseEntity<?> validFields(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = result.getFieldErrors().stream().map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        response = setApiResponse.setApiResponse(new ApiResponse<>(false, errors, ""));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    

    
}
