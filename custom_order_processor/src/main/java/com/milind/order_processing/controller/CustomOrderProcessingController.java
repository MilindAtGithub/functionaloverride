package com.milind.order_processing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milind.order_processing.service.CustomOrderProcessingService;


@RestController
public class CustomOrderProcessingController {

	private static Logger logger = LoggerFactory.getLogger(CustomOrderProcessingController.class);
	
	@Autowired
	CustomOrderProcessingService orderProcessing;
	
	@RequestMapping(value = "customorderprocess", method = RequestMethod.POST)
	public String customorderprocessing(@RequestBody String orderJson)  {
		logger.debug("Custom Processing the Received Order: "+orderJson);
		return orderProcessing.processOrder(orderJson);
	}

}
