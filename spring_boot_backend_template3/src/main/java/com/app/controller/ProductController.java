package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductDTO;
import com.app.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts()
	{
		List<ProductDTO> allProducts = productService.getAllProducts();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewProduct(@RequestBody ProductDTO product)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addNewProduct(product));
	}
	
	@PutMapping("/{pid}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "pid") Long pid, @RequestBody ProductDTO dto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(pid,dto));
	}
	
	@DeleteMapping("/{cid}/{pid}")
	public String deleteProduct(@PathVariable Long cid , @PathVariable Long pid)
	{
		return productService.deleteProduct(cid, pid);
		
	}
	
	
	
	

}
