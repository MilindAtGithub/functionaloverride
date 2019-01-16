package com.milind.order_processing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milind.order_processing.service.OrderProcessingService;


@RestController
public class OrderProcessingController {

	private static Logger logger = LoggerFactory.getLogger(OrderProcessingController.class);
	
	@Autowired
	OrderProcessingService orderProcessing;
	
	@RequestMapping(value = "orderprocess", method = RequestMethod.POST)
	public String activationLink(@RequestBody String orderJson)  {
		logger.debug("Processing the Received Order: "+orderJson);
		return orderProcessing.processOrder(orderJson);
	}

}
