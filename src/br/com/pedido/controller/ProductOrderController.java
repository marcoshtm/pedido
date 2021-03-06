package br.com.pedido.controller;

import java.io.IOException;
import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.joda.time.LocalDateTime;
import org.primefaces.event.SelectEvent;

import br.com.pedido.domain.order.OrderService;
import br.com.pedido.domain.product.ProductService;
import br.com.pedido.entity.Order;
import br.com.pedido.entity.OrderItem;
import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;

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
	
	private List<OrderItem> orderItems;
	private OrderItem selectedOrderItem;
	
	private LocalDateTime deliveryForecast;
	
	@PostConstruct
	public void init() {
		try {
			this.products = productService.getProducts();
			adjustProductsPositions();
			this.filteredProducts = products;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		this.orderItems = new ArrayList<OrderItem>();
	}
	
	public void onRowSelect(SelectEvent event) {
		Product selectedProduct = (Product) event.getObject();
		orderItems.add(new OrderItem(selectedProduct, 1));
		products.remove(selectedProduct);
    }
	
	public void minus(OrderItem order) {
		int index = orderItems.indexOf(order);
		order.setQuantity(order.getQuantity()-1);
		orderItems.set(index, order);
	}
	
	public void plus(OrderItem order) {
		int index = orderItems.indexOf(order);
		order.setQuantity(order.getQuantity()+1);
		orderItems.set(index, order);
	}
	
	public void submitOrder() {
		if (orderItems.size() == 0) {
			showErrorMessage("Selecione ao menos um produto.");
			return;
		}
		
		if (!orderService.getAcceptance()) {
			showErrorMessage("Seu pedido n�o foi aceito (randomicamente).");
			return;
		}
		
		try {
			LocalDateTime creation = LocalDateTime.now();
			this.deliveryForecast = orderService.calculateDeliveryForecast(creation);
			Order order = orderService.getOrderPartiallyMocked(creation, deliveryForecast, orderItems);
			orderService.setOrder(order);
			FacesContext.getCurrentInstance().getExternalContext().redirect("pagina2.xhtml");
		} catch (BusinessException be) {
			showErrorMessage("O envio do seu pedido para o webservice falhou.");
		} catch (IOException e) {
			e.printStackTrace();
			showErrorMessage("Erro inesperado.");
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
	
	public void removeOrderItem() {
		products.add(selectedOrderItem.getProduct());
		orderItems.remove(selectedOrderItem);
		adjustProductsPositions();
	}
	
	private void adjustProductsPositions() {
		Collections.sort(products, new Comparator<Product> () {  
			Collator collator = Collator.getInstance(new Locale("pt", "BR"));
			public int compare(Product p1, Product p2) {
				return collator.compare(p1.getName(), p2.getName());
	        }  
        });
	}
	
	public String prepareToFilter(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		str = str.replace("  ", " ");
		str = str.replace("   ", " ");
		str = str.trim();
		return str;
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
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	} 
	
	public void setOrders(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem getSelectedOrderItem() {
		return selectedOrderItem;
	}

	public void setSelectedOrderItem(OrderItem selectedOrderItem) {
		this.selectedOrderItem = selectedOrderItem;
	}
	
	public String getDeliveryForecast() {
		if (deliveryForecast != null) {
			return deliveryForecast.toString("HH:mm");
		} else {
			return "";
		}
	}
	
	public void setDeliveryForecast(LocalDateTime deliveryForecast) {
		this.deliveryForecast = deliveryForecast;
	}
}