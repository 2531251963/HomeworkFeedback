package com.example.demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties("filter")
@Data
public class FilterUrlConfig {

    private Set<String> notFilterUrl;

    public boolean isFilterUrl(String url){
        return notFilterUrl.contains(url);
    }
}
