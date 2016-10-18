package br.com.pedido.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ejb.Stateless;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.io.IOException;

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
			throw new BusinessException(e);
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
		         System.out.println(""+sb.toString());
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
	
	private List<Product> jsonToProducts(String jsonProducts) {
		// TODO Implementar este método
		return null;
	}
}
