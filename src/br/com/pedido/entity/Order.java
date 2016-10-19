package br.com.pedido.entity;

public class Order {
	private Product product;
	private Integer quantity;
	
	public Order(Product product, Integer quantity) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		if (product == null || product.getProductId() == null || quantity == null ||
			other.getProduct() == null || other.getProduct().getProductId() == null || other.getQuantity() == null) {
			return false;
		}
		return product.getProductId().equals(other.getProduct().getProductId()) && quantity.equals(other.quantity);
	}
}
