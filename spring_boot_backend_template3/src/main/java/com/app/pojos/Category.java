package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "category_tb")
public class Category extends BaseEntity {
	
	@Column(length = 20)
	private String cname;
	
	@OneToMany(mappedBy = "category", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<Product> products = new ArrayList<>();
	
	
	
	
	public Category(String cname, List<Product> products) {
		
		this.cname = cname;
		this.products = products;
	}
	
	

	public Category() {
		
	}



	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Category [cname=" + cname + ", products=" + products + "]";
	}
	public void addProduct(Product p) {
		products.add(p);
		p.setCategory(this);
	}
	public void removeProduct(Product p) {
		products.remove(p);
		p.setCategory(null);	
	}
	
	

}
