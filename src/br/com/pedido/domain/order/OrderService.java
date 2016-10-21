package br.com.pedido.domain.order;

import java.math.BigDecimal;
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
		Order order = new Order();
		order.setStoreId(1);
		order.setOrderId("1");
		order.setTotalAmount(new BigDecimal(100));
		order.setDeliveryFee(new BigDecimal(10));
		
		order.setPayment(orderMock.getPaymentMock());
		
		order.setCreation(creation);
		order.setDeliveryForecast(deliveryForecast);
		order.setOrderItems(orderItems);
		
		order.setCustomer(orderMock.getCustomerMock());
		
		return order;
	}	
}