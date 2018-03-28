package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Order {
	private int id;
	private int quantity;
	private int idClient;
	private int idProduct;

	public Order() {
	}

	public Order(int id, int quantity, int idClient, int idProduct) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.idClient = idClient;
		this.idProduct = idProduct;
	}

	public Order(int quantity, int idClient, int idProduct) {
		super();

		this.quantity = quantity;
		this.idClient = idClient;
		this.idProduct = idProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", quantity=" + quantity + ", idClient=" + idClient + ", idProduct=" + idProduct
				+ "]";
	}

}