package br.com.pedido.domain.order;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import br.com.pedido.exception.BusinessException;

@Stateless
public class OrderResponseJsonReader {
	public OrderResponse jsonToOrderResponse(String jsonOrderResponse) throws BusinessException {
		InputStream inputStream = new ByteArrayInputStream(jsonOrderResponse.getBytes(StandardCharsets.UTF_8));
		JsonReader jsonReader = Json.createReader(inputStream);
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Durante o parse do json da resposta ao envio do pedido, obtido do webservice, ocorreu um erro inesperado ao fechar o input stream.");
		}
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setStatus(jsonObject.getString("status"));
		orderResponse.setOrderId(jsonObject.getInt("id_pedido"));
		
		return orderResponse;
	}
}