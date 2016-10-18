package br.com.pedido.service;

import java.net.MalformedURLException;
import java.net.URL;
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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.InputStream;

@Stateless
public class ProductConnector {
	private final String GET_PRODUCTS_URL = "http://homolog.delivery.all4mobile.com.br/api/v1/getProdutos";
	private final String AUTH_KEY = "hello123";
	
	public List<Product> getProducts() throws BusinessException {
		try {
			URL url = new URL(GET_PRODUCTS_URL);
			String jsonProducts = getRemoteProducts(url);
			return jsonToProducts(jsonProducts);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new BusinessException("O url do webservice está mal formatado.");
		}
	}
	
	private String getRemoteProducts(URL url) throws BusinessException {
		try {
		    String inputJson = "{ \"authkey\":\"" + AUTH_KEY + "\"}";
		    
		    HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    con.setDoOutput(true);
		    con.setDoInput(true);
		    
		    con.setRequestProperty("Content-Type", "application/json");
		    con.setRequestProperty("Accept", "application/json");
		    con.setRequestProperty("Method", "POST");
		    OutputStream os = con.getOutputStream();
		    os.write(inputJson.getBytes("UTF-8"));
		    os.close();
		    
		    StringBuilder sb = new StringBuilder();
		    int HttpResult = con.getResponseCode();
		    if (HttpResult == HttpURLConnection.HTTP_OK) {
		    	BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		        String line = null;
		        while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		        }
		         br.close(); 
		    } else {
		    	String errorMessage = "Não foi possível obter a lista de produtos do webservice. HTTP error code: " + con.getResponseCode() + " Response message: " + con.getResponseMessage();
		    	System.out.println(errorMessage);
		    	throw new BusinessException(errorMessage);
		    }
			
			return sb.toString();
		} catch (ProtocolException e) {
			 e.printStackTrace();
			 throw new BusinessException("O protocolo utilizado para acesso ao webservice é inválido.");
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			throw new BusinessException("O host para obter os produtos por webservice não foi encontrado.");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new BusinessException("Não foi possível obter a lista de produtos do webservice. Ocorreu um erro de runtime.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Não foi possível obter a lista de produtos do webservice. Ocorreu um erro de IO.");
		}
	}
	
	private List<Product> jsonToProducts(String jsonProducts) throws BusinessException {
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
			
			Product product = jsonObjectToProduct(jsonObject.toString());
			products.add(product);
		}
		
		return products;
	}
	
	public Product jsonObjectToProduct(String jsonProduct) throws BusinessException {
		System.out.println(jsonProduct);
		
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
				product.setProductId(null);
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
		
//		System.out.println(NumberFormat.getCurrencyInstance().format(product.getPrice()));
//		System.out.println(product.getImg());
//		System.out.println(product.getProductId().toString());
//		System.out.println(product.getDetails());
//		System.out.println(product.getName());
//		System.out.println(product.getSku());
//		System.out.println(product.getDescription());
		
		return product;
	}
}
