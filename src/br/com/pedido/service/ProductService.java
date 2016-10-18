package br.com.pedido.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;

@Stateless
public class ProductService {
	@Inject ProductConnector productConnector;
	
	public List<Product> getProducts() throws BusinessException {
		return productConnector.getProducts();
	}
	
	public List<Product> getMockedProducts() {
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
