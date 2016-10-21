package br.com.pedido.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Payment {
	public String status;
	public String method;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public JsonObject toJsonObject() {
		JsonObjectBuilder paymentBuilder = Json.createObjectBuilder();
		paymentBuilder.add("pagamento_status", status);
		paymentBuilder.add("pagamento_forma", method);
		return paymentBuilder.build();
	}
}
