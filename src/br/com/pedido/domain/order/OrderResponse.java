package br.com.pedido.domain.order;

public class OrderResponse {
	private String status;
	private Integer orderId;
	
	public OrderResponse(String status, Integer orderId) {
		this.status = status;
		this.orderId = orderId;
	}
	
	public OrderResponse() {
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof OrderResponse)) {
			return false;
		}
		OrderResponse other = (OrderResponse) obj;
		if (orderId == null || other.orderId == null) {
			return false;
		}
		return orderId.equals(other.orderId);
	}
}