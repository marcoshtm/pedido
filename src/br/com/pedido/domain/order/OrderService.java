package br.com.pedido.domain.order;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.LocalDateTime;

import br.com.pedido.entity.Order;
import br.com.pedido.entity.OrderItem;
import br.com.pedido.exception.BusinessException;

@Stateless
public class OrderService {
	@Inject OrderConnector orderConnector;
	@Inject OrderMock orderMock;
	
	public Boolean getAcceptance() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public LocalDateTime calculateDeliveryForecast(LocalDateTime creation) {
		return creation.plusMinutes(50);
	}
	
	public void setOrder(Order order) throws BusinessException {
		orderConnector.setOrder(order);
	}
	
	public Order getOrderPartiallyMocked(LocalDateTime creation, LocalDateTime deliveryForecast, List<OrderItem> orderItems) {
		return orderMock.getOrderPartiallyMocked(creation, deliveryForecast, orderItems);
	}	
}