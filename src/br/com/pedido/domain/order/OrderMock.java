package br.com.pedido.domain.order;

import br.com.pedido.entity.Customer;
import br.com.pedido.entity.Payment;

public class OrderMock {
	public Payment getPaymentMock() {
		Payment payment = new Payment();
		payment.setStatus("nao_pago");
		payment.setMethod("dinheiro");
		return payment;
	}
	
	public Customer getCustomerMock() {
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