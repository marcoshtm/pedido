package br.com.pedido.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;

@Stateless
public class ProductService {
	@Inject ProductConnector productConnector;
	
	public List<Product> getProducts() throws BusinessException {
		return productConnector.getRemoteProducts();
	}
}