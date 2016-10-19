package br.com.pedido.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.pedido.entity.Order;
import br.com.pedido.entity.Product;

@Stateless
public class OrderService {
	public List<Order> getOrders() {
		return getOrdersMock();
	}

	private List<Order> getOrdersMock() {
		List<Order> orders = new ArrayList<Order>();
		
		Product p1 = new Product();
		p1.setProductId(1);
		p1.setName("Teste1");
		
		Order order1 = new Order(p1, 1);
		
		Product p2 = new Product();
		p2.setProductId(2);
		p2.setName("Teste2");
		
		Order order2 = new Order(p2, 2);
		
		orders.add(order1);
		orders.add(order2);
		
		return orders;
	}
}