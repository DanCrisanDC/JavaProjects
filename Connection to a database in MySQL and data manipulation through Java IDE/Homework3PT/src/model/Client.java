package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Client {
	private int idClient;
	private String name;
	private String address;
	private String phone;
	private int age;

	public Client() {
		super();
	}

	public Client(int id, String name, int age, String address, String phone) {
		super();
		this.idClient = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.age = age;
	}

	public Client(String name, int age, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.age = age;
	}

	public int getId() {
		return idClient;
	}

	public void setId(int id) {
		this.idClient = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Client [id=" + idClient + ", name=" + name + ", age=" + age + ", address=" + address + ", phone="
				+ phone + "]";
	}

}