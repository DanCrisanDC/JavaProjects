package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Product {
	private int idProduct;
	private String name;
	private int quantity;
	private String producer;
	private double price;

	public Product() {
	}

	public Product(int id, String name, double price, int quantity, String producer) {
		super();
		this.idProduct = id;
		this.name = name;
		this.quantity = quantity;
		this.producer = producer;
		this.price = price;
	}

	public Product(String name, double price, int quantity, String producer) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.producer = producer;
		this.price = price;
	}

	public int getId() {
		return idProduct;
	}

	public void setId(int id) {
		this.idProduct = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return "Product [id=" + idProduct + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", producer=" + producer + "]";
	}

}