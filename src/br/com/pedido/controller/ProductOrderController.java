package br.com.pedido.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.joda.time.LocalDateTime;
import org.primefaces.event.SelectEvent;

import br.com.pedido.entity.Order;
import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.OrderService;
import br.com.pedido.service.ProductService;

@SessionScoped
@ManagedBean
public class ProductOrderController {
	@Inject
	ProductService productService;
	@Inject
	OrderService orderService;
	
	private List<Product> products;
	private List<Product> filteredProducts;
	private Product selectedProduct;
	
	private List<Order> orders;
	
	private LocalDateTime deliveryDateTime;
	
	@PostConstruct
	public void init() {
		try {
			this.products = productService.getProducts();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		this.orders = new ArrayList<Order>();
	}
	
	public void onRowSelect(SelectEvent event) {
		Product selectedProduct = (Product) event.getObject();
		orders.add(new Order(selectedProduct, 1));
		products.remove(selectedProduct);
    }
	
	public void minus(Order order) {
		int index = orders.indexOf(order);
		order.setQuantity(order.getQuantity()-1);
		orders.set(index, order);
	}
	
	public void plus(Order order) {
		int index = orders.indexOf(order);
		order.setQuantity(order.getQuantity()+1);
		orders.set(index, order);
	}
	
	public void submitOrder() {
		if (orders.size() == 0) {
			showErrorMessage("Selecione ao menos um produto.");
			return;
		}
		
		Boolean accepted = orderService.getAcceptance();
		
		if (!accepted) {
			showErrorMessage("O pedido não foi aceito (randomicamente).");
		} else {
			this.deliveryDateTime = LocalDateTime.now().plusMinutes(50);
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pagina2.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
				showErrorMessage("Seu pedido não foi realizado. Erro inesperado.");
			}
		}
	}
	
	private void showErrorMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message,  null);
		FacesContext.getCurrentInstance().addMessage("messages", facesMessage);
	}
	
	public void newOrder() {
		init();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pagina1.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getProducts() {
        return products;
	}
	
	public List<Product> getFilteredProducts() {
		return this.filteredProducts;
	}
	
	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	public List<Order> getOrders() {
		return orders;
	} 
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getDeliveryDateTime() {
		if (deliveryDateTime != null) {
			return deliveryDateTime.toString("HH:mm");
		} else {
			return "";
		}
	}
	
	public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}
}