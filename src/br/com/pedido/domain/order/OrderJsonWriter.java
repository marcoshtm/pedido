package br.com.pedido.domain.order;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import br.com.pedido.entity.Order;
import br.com.pedido.entity.OrderItem;
import br.com.pedido.service.JsonUtil;

@Stateless
public class OrderJsonWriter {
	@Inject JsonUtil jsonUtil;
	
	public String orderToJson(Order order, String authKey) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		
		builder.add("id_loja", order.getStoreId());
		builder.add("authkey", authKey);
		builder.add("id_pedido", order.getOrderId());
		builder.add("dthr_criacao", order.getCreation().toString("ddmmyyyyHHmm"));
		builder.add("previsao_entrega", order.getDeliveryForecast().toString("ddmmyyyyHHmm"));
		builder.add("total", order.getTotalAmount());
		builder.add("frete", order.getDeliveryFee());
		builder.add("pagamento", order.getPayment().toJsonObject());
		builder.add("itens", orderItemstoJsonArray(order.getOrderItems()));
		builder.add("cliente", order.getCustomer().toJsonObject());
		
		JsonObject ordersJsonObject = builder.build();
		
		return jsonUtil.jsonObjectToJson(ordersJsonObject);
	}
	
	public JsonArray orderItemstoJsonArray(List<OrderItem> orderItems) {
		JsonArrayBuilder orderItemsJsonArrayBuilder = Json.createArrayBuilder();
		for (OrderItem orderItem: orderItems) {
			orderItemsJsonArrayBuilder.add(orderItem.toJsonObject());
		}
		return orderItemsJsonArrayBuilder.build();
	}
}