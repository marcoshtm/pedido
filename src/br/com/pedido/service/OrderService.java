package br.com.pedido.service;

import java.util.Random;
import javax.ejb.Stateless;

@Stateless
public class OrderService {
	public Boolean getAcceptance() {
		Random random = new Random();
		return random.nextBoolean();
	}
}