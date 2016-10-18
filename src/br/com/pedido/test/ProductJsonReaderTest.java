package br.com.pedido.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.ProductJsonReader;

public class ProductJsonReaderTest {
	@Test
	public void jsonObjectToProductBasicTest() {
		try {
			String jsonProduct = "{\"preco\":3.6,\"imagem\":\"vKSXY73\",\"id_produto\":1058,\"detalhes\":\"01 UNIDADE\",\"nome\":\"ABACAXI CARAMELADO 01 UNIDADE\",\"sku\":\"602\",\"descricao\":\"Abacaxi envolvido em massa leve caramelada.\"}";
			ProductJsonReader productJsonReader = new ProductJsonReader();
			Product product = productJsonReader.jsonToProduct(jsonProduct);
			assertTrue(product.getName().equals("ABACAXI CARAMELADO 01 UNIDADE"));
		} catch (BusinessException e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}
}