package br.com.pedido.service;

import java.util.ArrayList;
import java.util.List;

import br.com.pedido.entity.Product;

public class ProductService {
	public List<Product> getProducts() {
		Product product1 = new Product();
		product1.setName("ABACAXI CARAMELADO 01 UNIDADE");
		
		Product product2 = new Product();
		product2.setName("ÁGUA - COM GÁS");
		
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		return products;
	}
}
