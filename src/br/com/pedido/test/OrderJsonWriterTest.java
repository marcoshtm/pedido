package br.com.pedido.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.json.JsonObject;

import org.junit.Test;

import br.com.pedido.entity.OrderItem;
import br.com.pedido.entity.Product;
import br.com.pedido.global.JsonUtil;

public class OrderJsonWriterTest {
	@Test
	public void orderItemToJsonBasicTest() {
		Product product = new Product();
		product.setPrice(new BigDecimal(10));
		product.setSku("901");
		OrderItem order = new OrderItem(product, 1);
		
		JsonObject orderItemJsonObject = order.toJsonObject();
		
		JsonUtil jsonUtil = new JsonUtil();
		String orderItemJson = jsonUtil.jsonObjectToJson(orderItemJsonObject);
		
		assertTrue(orderItemJson.equals("{\"preco\":10,\"quantidade\":1,\"sku\":\"901\"}"));
	}
}