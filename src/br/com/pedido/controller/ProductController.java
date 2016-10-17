package br.com.pedido.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.service.ProductService;

@RequestScoped
@ManagedBean
public class ProductController {
	@Inject
	ProductService productService;
	
	private List<Product> products;
	private List<Product> filteredProducts;
	
	@PostConstruct
	public void init() {
		this.products = productService.getProducts();
	}
	
	public List<Product> getProducts() {
        return products;
	}
	
	public List<Product> getFilteredProducts() {
		return this.filteredProducts;
	}
	
	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}
}