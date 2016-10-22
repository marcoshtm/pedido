package br.com.pedido.domain.product;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.global.WebserviceConnector;

@Stateless
public class ProductConnector {
	@Inject ProductJsonReader productJsonReader;
	@Inject WebserviceConnector webserviceConnector;
	
	private final String GET_PRODUCTS_URL = "http://homolog.delivery.all4mobile.com.br/api/v1/getProdutos";
	private final String AUTH_KEY = "hello123";
	
	public List<Product> getRemoteProducts() throws BusinessException {
		try {
			URL url = new URL(GET_PRODUCTS_URL);
			String inputJson = "{ \"authkey\":\"" + AUTH_KEY + "\"}";
			
			String jsonProducts = webserviceConnector.getWebServiceJson(url, inputJson);
			return productJsonReader.jsonToProducts(jsonProducts);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new BusinessException("O url do webservice está mal formatado.");
		}
	}
}