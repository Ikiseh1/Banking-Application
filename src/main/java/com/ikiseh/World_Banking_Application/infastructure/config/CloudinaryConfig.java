package com.ikiseh.World_Banking_Application.infastructure.config;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = System.getenv("CLOUD_NAME_HIDE");

    private final String API_KEY = System.getenv("API_KEY_HIDE");

    private final String API_SECRET = System.getenv("API_SECRET_KEY_HIDE");

    @Bean

    //this helps in uploading images
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret",API_SECRET);


        return new Cloudinary(config);
    }

}
