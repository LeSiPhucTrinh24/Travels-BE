package com.lspt.Travels_BE.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary configKey(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfg5vgjtg");
        config.put("api_key", "531558939355971");
        config.put("api_secret", "EqObV3PuE8nNrmfoKNv7mcpRlj8");
        return new Cloudinary(config);
    }
}
