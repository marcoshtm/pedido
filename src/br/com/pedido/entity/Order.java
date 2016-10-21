package br.com.pedido.entity;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDateTime;

public class Order {
	public Integer storeId;
	public String orderId;
	public LocalDateTime creation;
	public LocalDateTime deliveryForecast;
	public BigDecimal totalAmount;
	public BigDecimal deliveryFee;
	public Payment payment;
	public List<OrderItem> orderItems;
	public Customer customer;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getCreation() {
		return creation;
	}
	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}
	public LocalDateTime getDeliveryForecast() {
		return deliveryForecast;
	}
	public void setDeliveryForecast(LocalDateTime deliveryForecast) {
		this.deliveryForecast = deliveryForecast;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		if (orderId == null || other.orderId == null) {
			return false;
		}
		return orderId.equals(other.orderId);
	}
}
