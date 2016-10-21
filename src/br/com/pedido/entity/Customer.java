package br.com.pedido.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Customer {
	private String addressStreet;
	private String addressNumber;
	private String addressCity;
	private String addressUf;
	private String addressNeighborhood;
	private String addressComplement;
	private String addressZipCode;
	private String name;
	private String phoneNumber;
	
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressUf() {
		return addressUf;
	}
	public void setAddressUf(String addressUf) {
		this.addressUf = addressUf;
	}
	public String getAddressNeighborhood() {
		return addressNeighborhood;
	}
	public void setAddressNeighborhood(String addressNeighborhood) {
		this.addressNeighborhood = addressNeighborhood;
	}
	public String getAddressComplement() {
		return addressComplement;
	}
	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
	public String getAddressZipCode() {
		return addressZipCode;
	}
	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public JsonObject toJsonObject() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("endereco_rua", addressStreet);
		builder.add("endereco_numero", addressNumber);
		builder.add("endereco_cidade", addressCity);
		builder.add("endereco_uf", addressUf);
		builder.add("endereco_bairro", addressNeighborhood);
		builder.add("endereco_complemento", addressNumber);
		builder.add("endereco_cep", addressZipCode);
		builder.add("cliente_nome", name);
		builder.add("cliente_celular", phoneNumber);
		return builder.build();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		if (name == null || other == null || other.getName() == null) {
			return false;
		}
		return name.equals(other.getName());
	}
}