package queue;

import java.util.*;

public class Simulation extends Thread {

	private volatile int nrOfQ;
	public static int simTime;
	private static List<Queue> que = new ArrayList<Queue>();
	private int minArr;
	private int maxArr;
	private int minSer;
	private int maxSer;
	private int peak;
	private int peakMax;
	private int peakTime;
	public static int startTime = 0;

	public Simulation(int minArr, int maxArr, int minSer, int maxSer, int nrOfQ, int simTime) {
		this.minArr = minArr;
		this.maxArr = maxArr;
		this.minSer = minSer;
		this.maxSer = maxSer;
		this.nrOfQ = nrOfQ;
		this.simTime = simTime;
	}

	public void run() {
		for (int i = 0; i < nrOfQ; i++) {
			que.add(new Queue(i));
		}

		for (int i = 0; i < nrOfQ; i++) {
			que.get(i).start();
		}

		int arrival = 0;
		int service = 0;
		int id = 0;
		Random r = new Random();
		int minTimeId = 0;
		while (startTime < simTime) {
			arrival = r.nextInt(maxArr - minArr + 1) + minArr;
			service = r.nextInt(maxSer - minSer + 1) + minSer;
			if (startTime / 1000 + service < simTime / 1000) {
				Customer c = new Customer(id, startTime, service);
				id++;
				System.out.printf("Customer id: %d, customer arrival: %d, customer service: %d, joined queue: %d\n",
						id - 1, startTime / 1000, service, minTimeId);
				que.get(minTimeId).addCust(c);
				peak = 0;
				for (Queue q : que) {
					peak += q.getQueueSize();
				}
				if (peak > peakMax) {
					peakMax = peak;
					peakTime = startTime / 1000;
				}
				try {
					sleep(arrival * 1000);
				} catch (InterruptedException e) {
					System.out.println("Interuption");
					System.out.println(e.toString());
				}
				minTimeId = getMinWaitTime(que);
			}
			startTime = startTime + arrival * 1000;
		}
		for (Queue q : que) {
			q.setWorks(false);
		}
		System.out.printf("Peak time was at time: %d\n", peakTime);
	}

	public int getMinWaitTime(List<Queue> que) {
		int id = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < que.size(); i++) {
			if (min > que.get(i).getWaitTime()) {
				min = que.get(i).getWaitTime();
				id = i;
			}
		}
		return id;
	}
}
