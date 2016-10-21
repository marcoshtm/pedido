package br.com.pedido.domain.order;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.entity.Order;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.WebserviceConnector;

@Stateless
public class OrderConnector {
	@Inject WebserviceConnector webserviceConnector;
	@Inject OrderJsonWriter orderJsonWriter;
	
	private final String SET_ORDER_URL = "http://homolog.delivery.all4mobile.com.br/api/v1/setPedido";
	private final String AUTH_KEY = "hello123";
	
	public Boolean setOrder(Order order) throws BusinessException {
		try {
			URL url = new URL(SET_ORDER_URL);
			String jsonOrder = orderJsonWriter.orderToJson(order, AUTH_KEY);
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
}
