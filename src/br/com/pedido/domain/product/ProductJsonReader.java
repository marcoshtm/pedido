package br.com.pedido.domain.product;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;

@Stateless
public class ProductJsonReader {
	public List<Product> jsonToProducts(String jsonProducts) throws BusinessException {
		InputStream inputStream = new ByteArrayInputStream(jsonProducts.getBytes(StandardCharsets.UTF_8));
		JsonReader jsonReader = Json.createReader(inputStream);
		JsonObject jsonRootObject = jsonReader.readObject();
		jsonReader.close();
		
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Durante o parse do json dos produtos, obtido do webservice, ocorreu um erro inesperado ao fechar o input stream.");
		}
		
		List<Product> products = new ArrayList<Product>();
		int index = 0;
		JsonArray jsonArray = jsonRootObject.getJsonArray("produtos");
		ListIterator<JsonValue> listIterator = jsonArray.listIterator();
		
		while (listIterator.hasNext()) {
			JsonObject jsonObject = jsonArray.getJsonObject(index);
			index++;
			listIterator.next();
			
			Product product = jsonToProduct(jsonObject.toString());
			products.add(product);
		}
		
		return products;
	}
	
	public Product jsonToProduct(String jsonProduct) throws BusinessException {
		InputStream inputStream = new ByteArrayInputStream(jsonProduct.getBytes(StandardCharsets.UTF_8));
		JsonReader jsonReader = Json.createReader(inputStream);
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		Product product = new Product();
		
		try {
			try {
				product.setPrice(jsonObject.getJsonNumber("preco").bigDecimalValue().setScale(2, RoundingMode.HALF_UP));
			} catch (NullPointerException npe) {
				product.setPrice(null);
			}
			try {
				product.setImg(jsonObject.getString("imagem"));
			} catch (NullPointerException npe) {
				product.setImg(null);
			}
			try {
				product.setProductId(jsonObject.getInt("id_produto"));
			} catch (NullPointerException npe) {
				npe.printStackTrace();
				throw new BusinessException("O json obtido do webservice possui ao menos um produto que não contém o id_produto.");
			}
			try {
				product.setDetails(jsonObject.getString("detalhes"));
			} catch (NullPointerException npe) {
				product.setDetails(null);
			}
			try {
				product.setName(jsonObject.getString("nome"));
			} catch (NullPointerException npe) {
				npe.printStackTrace();
				throw new BusinessException("O json obtido do webservice possui ao menos um produto que não contém o nome.");
			}
			try {
				product.setSku(jsonObject.getString("sku"));
			} catch (NullPointerException npe) {
				product.setSku(null);
			}
			try {
				product.setDescription(jsonObject.getString("descricao"));
			} catch (NullPointerException npe) {
				product.setDescription(null);
			}
		} catch (ClassCastException cce) {
			cce.printStackTrace();
			throw new BusinessException("O json obtido do webservice possui ao menos um registro com tipo fora do padrão esperado.");
		}

		return product;
	}
}
