package com.milind.orderservice.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestComponent {

    @Autowired
    RestTemplate restTemplate;

    /**
     * This will execute and get the response
     * @param url
     * @param httpMethod
     * @param entity
     * @param cls
     * @return
     */
    public Object executeRequest(String url, HttpMethod httpMethod, HttpEntity entity, Class cls ){

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, cls);
        return response.getBody();
    }

    /**
     * This will return he new HTTP Entity
     * @param json
     * @param httpHeaders
     * @return
     */
    public HttpEntity getHTTPEntity(String json, HttpHeaders httpHeaders){
        return new HttpEntity<String>(json,httpHeaders);
    }
}
