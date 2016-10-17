package br.com.pedido.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.service.ProductService;

@RequestScoped
@ManagedBean
public class ProductRequestController {
	@Inject
	ProductService productService;

	public List<Product> getProducts() {
        return productService.getProducts();
	}
}