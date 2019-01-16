package com.milind.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milind.orderservice.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@RequestMapping(value = "createOrder", method = RequestMethod.POST)
	public String activationLink(@RequestBody String orderJson)  {
		logger.debug("Creating the Order JSON: "+orderJson);
		return orderService.createOrder(orderJson);
	}

}
