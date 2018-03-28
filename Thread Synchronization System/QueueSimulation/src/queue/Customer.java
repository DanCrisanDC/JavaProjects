package queue;

public class Customer {

	private int service;
	private int arrival;
	private final int id;

	public Customer(int id, int arrival, int service) {
		this.id = id;
		this.arrival = arrival;
		this.service = service;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	public int getId() {
		return id;
	}
}
