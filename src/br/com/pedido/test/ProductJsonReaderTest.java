package br.com.pedido.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.ProductJsonReader;

public class ProductJsonReaderTest {
	@Test
	public void jsonToProductBasicTest() {
		try {
			String jsonProduct = "{\"preco\":3.6,\"imagem\":\"vKSXY73\",\"id_produto\":1058,\"detalhes\":\"01 UNIDADE\",\"nome\":\"ABACAXI CARAMELADO 01 UNIDADE\",\"sku\":\"602\",\"descricao\":\"Abacaxi envolvido em massa leve caramelada.\"}";
			ProductJsonReader productJsonReader = new ProductJsonReader();
			Product product = productJsonReader.jsonToProduct(jsonProduct);
			assertTrue(product.getPrice().compareTo(new BigDecimal(3.6).setScale(2, RoundingMode.HALF_UP))==0);
			assertTrue(product.getImg().equals("vKSXY73"));
			assertTrue(product.getProductId().equals(1058));
			assertTrue(product.getDetails().equals("01 UNIDADE"));
			assertTrue(product.getName().equals("ABACAXI CARAMELADO 01 UNIDADE"));
			assertTrue(product.getSku().equals("602"));
			assertTrue(product.getDescription().equals("Abacaxi envolvido em massa leve caramelada."));
		} catch (BusinessException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}