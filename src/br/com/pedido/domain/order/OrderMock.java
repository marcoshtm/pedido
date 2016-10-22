package br.com.pedido.domain.order;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDateTime;

import br.com.pedido.entity.Customer;
import br.com.pedido.entity.Order;
import br.com.pedido.entity.OrderItem;
import br.com.pedido.entity.Payment;

public class OrderMock {
	public Order getOrderPartiallyMocked(LocalDateTime creation, LocalDateTime deliveryForecast, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setStoreId(1);
		order.setOrderId("1");
		order.setTotalAmount(new BigDecimal(100));
		order.setDeliveryFee(new BigDecimal(10));
		
		order.setPayment(this.getPaymentMock());
		
		order.setCreation(creation);
		order.setDeliveryForecast(deliveryForecast);
		order.setOrderItems(orderItems);
		
		order.setCustomer(this.getCustomerMock());
		
		return order;
	}
	
	private Payment getPaymentMock() {
		Payment payment = new Payment();
		payment.setStatus("nao_pago");
		payment.setMethod("dinheiro");
		return payment;
	}
	
	private Customer getCustomerMock() {
		Customer customer = new Customer();
		customer.setAddressStreet("Rua da Leitura");
		customer.setAddressNumber("1000");
		customer.setAddressCity("Porto Alegre");
		customer.setAddressUf("RS");
		customer.setAddressNeighborhood("Centro");
		customer.setAddressNumber("20");
		customer.setAddressZipCode("90570020");
		customer.setName("Maria Silva");
		customer.setPhoneNumber("99667012");
		return customer;
	}
}