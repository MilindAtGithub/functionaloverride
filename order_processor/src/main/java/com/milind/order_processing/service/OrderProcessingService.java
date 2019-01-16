package com.milind.order_processing.service;

import com.milind.order_processing.annotation.OverideAnnotation;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Order Processing Service
 */
@Component
public class OrderProcessingService {

	private static Logger logger = LoggerFactory.
			getLogger(OrderProcessingService.class);

	// Giving the keys for annotation which will get loaded in aspect
	@OverideAnnotation(enabled =  "orderprocess.enabled", serviceName ="orderprocess.serviceName",
			requestPath = "orderprocess.requestPath",responseType = "orderprocess.responseType")
	public String processOrder(String json) {
		logger.debug("Proceesed the Order: "+json);
		return "From Product:"+Long.toString(RandomUtils.nextLong());
	}
}
