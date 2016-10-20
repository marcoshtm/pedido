package br.com.pedido.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.pedido.domain.order.OrderResponse;
import br.com.pedido.domain.order.OrderResponseJsonReader;
import br.com.pedido.exception.BusinessException;

public class OrderResponseJsonReaderTest {
	@Test
	public void jsonToOrderResponseBasicTest() {
		try {
			String jsonOrderResponse = "{"
					+ "\"status\": \"OK\","
					+ "\"id_pedido\": 15143"
					+ "}";
			OrderResponseJsonReader orderResponseJsonReader = new OrderResponseJsonReader();
			OrderResponse orderResponse = orderResponseJsonReader.jsonToOrderResponse(jsonOrderResponse);
			assertTrue(orderResponse.getStatus().equals("OK"));
			assertTrue(orderResponse.getOrderId().equals(15143));
		} catch (BusinessException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}