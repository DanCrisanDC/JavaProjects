package queue;

import java.util.*;

public class Queue extends Thread {

	private final int id;
	private volatile int waitTime;
	private boolean works;
	private volatile List<Customer> cust = new ArrayList<Customer>();
	private int sumS;
	private int noOfCust;
	private int sumW;

	public Queue(int id) {
		this.id = id;
		this.waitTime = 0;
		this.works = true;
		this.sumS = 0;
		this.sumW = 0;
		this.noOfCust = 0;
	}

	public int getQueueId() {
		return id;
	}

	public int getQueueSize() {
		return cust.size();
	}

	public void addCust(Customer c) {
		cust.add(c);
		noOfCust++;
		sumS += c.getService();
	}

	public void delCust() {
		Customer c = cust.get(0);
		System.out.printf("Customer %d left queue %d\n", cust.get(0).getId(), this.getQueueId());
		cust.remove(c);
	}

	public void setWorks(boolean works) {
		this.works = works;
	}

	public int getWaitTime() {

		waitTime = 0;
		for (Customer c : cust) {
			waitTime += c.getService();
		}
		return waitTime;
	}

	public float getAvgS() {
		return (float) sumS / (float) noOfCust;
	}

	public float getAvgW() {
		return (float) sumW / (float) noOfCust;
	}

	public int getSimTime() {
		return Simulation.simTime;
	}

	public int getCurTime() {
		return Simulation.startTime;
	}

	public void run() {
		while (this.works == true || cust.size() != 0) {
			if (cust.size() != 0) {
				try {
					sumW += this.getCurTime() - cust.get(0).getArrival();
					Thread.sleep(cust.get(0).getService() * 1000);
					delCust();
				} catch (Exception e) {
					System.out.println("Interuption");
					System.out.println(e.toString());
				}
			}
		}
		System.out.printf("Avg service time for queue %d: %.2f\n", this.getQueueId(), this.getAvgS());
		System.out.printf("Avg waiting time for queue %d: %.2f\n", this.getQueueId(), this.getAvgW() / 1000);
		if (this.getSimTime() / 1000 - sumS < 0)
			System.out.printf("Empty time for queue %d is: %d\n", this.getQueueId(), 0);
		else
			System.out.printf("Empty time for queue %d is: %d\n", this.getQueueId(), this.getSimTime() / 1000 - sumS);
	}
}
