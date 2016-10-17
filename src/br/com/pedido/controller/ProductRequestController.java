package br.com.pedido.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.pedido.service.ProductService;

@RequestScoped
@ManagedBean
public class ProductRequestController {
	@Inject
	ProductService productService;
	
	@PostConstruct
	public void init(){
		System.out.println(" Bean executado2! ");
	}
	
	public String getMessage(){
		return "Hello World JSF!!";
	}
	
	public List<String> getProducts() {
        return productService.getProducts();
	}
}