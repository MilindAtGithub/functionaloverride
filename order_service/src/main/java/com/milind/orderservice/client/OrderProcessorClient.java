package com.milind.orderservice.client;

import java.util.Arrays;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Component
public class OrderProcessorClient {

	@Autowired
	EurekaClient eurekaClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${app.orderProcessName.name:orderprocessor}")
    private String orderServiceName;
	
	public String processOrder(String json) {
		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeaders("coorelationId",  MDC.get("coorelationId")));
		String url = instanceUrl(orderServiceName)+"/orderprocess";
		HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}
	
	
	private String instanceUrl(String serviceName) {
		
		Application application = eurekaClient.getApplication(serviceName);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		return "http://"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort();
	}
	
	
	private HttpHeaders getHeaders(String key, String value) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.set(key, value);
		return headers;
	}
}
