package br.com.pedido.domain.order;

import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedido.exception.BusinessException;

@Stateless
public class OrderService {
	@Inject OrderConnector orderConnector;
	
	public Boolean getAcceptance() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public void setOrder() throws BusinessException {
		orderConnector.setOrder();
	}
}