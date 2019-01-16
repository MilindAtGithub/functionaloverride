package com.milind.orderservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order(1)
public class LogFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		MDC.put("coorelationId",Integer.toString(RandomUtils.nextInt()));
		chain.doFilter(request, response);
		
	}

}
