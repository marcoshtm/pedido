package br.com.pedido.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class OrderItem {
	private Product product;
	private Integer quantity;
	
	public OrderItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public JsonObject toJsonObject() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("preco", product.getPrice());
		builder.add("quantidade", quantity);
		builder.add("sku", product.getSku());
		return builder.build();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof OrderItem)) {
			return false;
		}
		OrderItem other = (OrderItem) obj;
		if (product == null || product.getProductId() == null || quantity == null ||
			other.getProduct() == null || other.getProduct().getProductId() == null || other.getQuantity() == null) {
			return false;
		}
		return product.getProductId().equals(other.getProduct().getProductId()) && quantity.equals(other.quantity);
	}
}
