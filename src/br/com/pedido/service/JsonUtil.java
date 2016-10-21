package br.com.pedido.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

@Stateless
public class JsonUtil {
	public String jsonObjectToJson(JsonObject ordersJsonObject) {
		OutputStream outputStream = new ByteArrayOutputStream();
		JsonWriter jsonWriter = Json.createWriter(outputStream);
		jsonWriter.writeObject(ordersJsonObject);
		jsonWriter.close();
		
		return outputStream.toString();
	}
}