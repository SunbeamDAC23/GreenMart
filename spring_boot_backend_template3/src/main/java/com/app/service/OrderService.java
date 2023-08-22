package com.app.service;

import com.app.dto.OrderItemsDTO;

public interface OrderService {
	
	OrderItemsDTO placeNewOrder(OrderItemsDTO dto);
	
	OrderItemsDTO deleteOrder(Long id);

}
