package br.com.pedido.domain.order;

import br.com.pedido.entity.Customer;
import br.com.pedido.entity.Payment;

public class OrderMock {
	public Payment getPaymentMock() {
		Payment payment = new Payment();
		payment.setStatus("nao_pago");
		payment.setMethod("dinheiro");
		return payment;
	}
	
	public Customer getCustomerMock() {
		Customer customer = new Customer();
		customer.setAddressStreet("Rua da Leitura");
		customer.setAddressNumber("1000");
		customer.setAddressCity("Porto Alegre");
		customer.setAddressUf("RS");
		customer.setAddressNeighborhood("Centro");
		customer.setAddressNumber("20");
		customer.setAddressZipCode("90570020");
		customer.setName("Maria Silva");
		customer.setPhoneNumber("99667012");
		return customer;
	}
	
	public String getOrderJsonMock() {
		String orderJson = "{"
				+"  \"id_loja\":1,"
				+"	\"authkey\":\"hello123\","
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