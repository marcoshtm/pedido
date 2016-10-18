package br.com.pedido.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
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
		try {
			this.products = productService.getProducts();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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