package br.com.pedido.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.ejb.Stateless;

import br.com.pedido.exception.BusinessException;

@Stateless
public class WebserviceConnector {
	public String getWebServiceJson(URL url, String inputJson) throws BusinessException {
		try {
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
		    	throw new BusinessException("Não foi possível obter o retorno do webservice. HTTP error code: " + con.getResponseCode() + " Response message: " + con.getResponseMessage());
		    }
		    
			return sb.toString();
			
		} catch (ProtocolException e) {
			 e.printStackTrace();
			 throw new BusinessException("O protocolo utilizado para acesso ao webservice é inválido.");
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			throw new BusinessException("O host para a chamada do webservice não foi encontrado.");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new BusinessException("Não foi possível obter o retorno do webservice. Ocorreu um erro de runtime.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Não foi possível obter o retorno do webservice. Ocorreu um erro de IO.");
		}
	}
}
