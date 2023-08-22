package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customExceptions.ResourceNotFoundException;
import com.app.dto.OrderItemsDTO;
import com.app.dto.ProductLineDTO;
import com.app.pojos.Order;
import com.app.pojos.ProductLine;
import com.app.repository.OrderRepository;
import com.app.repository.ProductLineRepository;
import com.app.repository.ProductRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductLineRepository lineRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@Override
	public OrderItemsDTO placeNewOrder(OrderItemsDTO dto) {
		
		System.out.println(dto.toString());
		
		List<ProductLineDTO> items = dto.getItems();
		Order newOrder = mapper.map(dto, Order.class);
		System.out.println(newOrder.toString());
		orderRepo.save(newOrder);
		
		for(ProductLineDTO item : items)
		{
			ProductLine productLineItem = new ProductLine();
			productLineItem.setOrder(newOrder);
			
			productLineItem.setProduct(productRepo.findById(item.getPid())
					.orElseThrow(()-> new ResourceNotFoundException("Product Not available")));
			productLineItem.setQty(item.getQty());
			lineRepo.save(productLineItem);
		}
		
		
		return mapper.map(newOrder, OrderItemsDTO.class);
	}

	@Override
	public OrderItemsDTO deleteOrder(Long id) {
		
		return null;
	}

}
