package com.milind.order_processing.service;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomOrderProcessingService {

	private static Logger logger = LoggerFactory.getLogger(CustomOrderProcessingService.class);
	public String processOrder(String json) {
		logger.debug("Proceesed the Order: "+json);
		return "Custom: "+Long.toString(RandomUtils.nextLong());
	}
}
