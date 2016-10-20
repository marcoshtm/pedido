package br.com.pedido.domain.order;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.WebserviceConnector;

@Stateless
public class OrderConnector {
	@Inject WebserviceConnector webserviceConnector;
	
	private final String SET_ORDER_URL = "http://homolog.delivery.all4mobile.com.br/api/v1/setPedido";
	private final String AUTH_KEY = "hello123";
	
	public Boolean setOrder() throws BusinessException {
		try {
			URL url = new URL(SET_ORDER_URL);
			String jsonOrder = getOrderJsonMock();
			webserviceConnector.getWebServiceJson(url, jsonOrder);
			return true;
		} catch (BusinessException be) {
			be.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new BusinessException("O url do webservice está mal formatado.");
		}
	}
	
	private String getOrderJsonMock() {
		String orderJson = "{"
				+"\"id_loja\":1,"
				+"	\"authkey\":\"" + AUTH_KEY + "\","
				+"	\"id_pedido\":\"101\","
				+"	\"dthr_criacao\":\"101120161615\","
				+"	\"previsao_entrega\":\"111120161715\","
				+"	\"total\":10.6,"
				+"	\"frete\":2,"
				+"	\"pagamento\":{"
				+"		\"pagamento_status\":\"nao_pago\","
				+"		\"pagamento_forma\":\"dinheiro\""
				+"	},"
				+"	\"itens\":["
				+"		{"
				+"			\"preco\":10.9,"
				+"			\"quantidade\":1,"
				+"			\"sku\":\"901\""
				+"		}, {"
				+"			\"preco\":10,"
				+"			\"quantidade\":1,"
				+"			\"sku\":\"902\""
				+"		}],"
				+"	\"cliente\":{"
				+"		\"endereco_rua\":\"Rua Um\","
				+"		\"endereco_numero\":\"100\","
				+"		\"endereco_cidade\":\"Porto Alegre\","
				+"		\"endereco_uf\":\"RS\","
				+"		\"endereco_bairro\":\"Centro\","
				+"     	\"endereco_complemento\":\"20\","
				+"       \"endereco_cep\":\"90820180\","
				+"       \"cliente_nome\":\"Luiza Nunes\","
				+"       \"cliente_celular\":\"99667012\""
				+"	}"
				+"}";
		
		return orderJson;
	}
}
