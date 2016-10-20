package br.com.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import br.com.pedido.entity.Order;
import br.com.pedido.entity.Product;
import br.com.pedido.exception.BusinessException;
import br.com.pedido.service.OrderService;
import br.com.pedido.service.ProductService;

@SessionScoped
@ManagedBean
public class ProductController {
	@Inject
	ProductService productService;
	@Inject
	OrderService orderService;
	
	private List<Product> products;
	private List<Product> filteredProducts;
	private Product selectedProduct;
	
	private List<Order> orders;
	
	@PostConstruct
	public void init() {
		try {
			this.products = productService.getProducts();
			orders = new ArrayList<Order>();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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
		Boolean accepted = orderService.getAcceptance();
		
		if (accepted) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso. Pedido aceito.",  null);
			FacesContext.getCurrentInstance().addMessage("messages", message);
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O pedido não foi aceito.",  null);
			FacesContext.getCurrentInstance().addMessage("messages", message);
		}
		
//		DateTime submitOrderDateTime = DateTime.now();
//		DateTime deliveryDateTime = submitOrderDateTime.plusMinutes(50);
		
//		try {
//			FacesContext.getCurrentInstance().getExternalContext().redirect("pagina2.xhtml");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
}