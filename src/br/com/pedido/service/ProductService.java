package br.com.pedido.service;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
	public List<String> getProducts() {
		List<String> products = new ArrayList<String>();
		products.add("produto1a");
		products.add("produto2b");
		return products;
	}
}
