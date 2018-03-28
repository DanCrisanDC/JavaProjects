package BT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Person implements Serializable, Observer {

	private static final long serialVersionUID = 1113799434508676095L;
	public String name;
	public String address;
	public List<Account> acc = new ArrayList<Account>();
	
	public Person(String name, String address, List<Account> acc) {
		this.name = name;
		this.address = address;
		this.acc = acc;
	}
	
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Person() {
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
	public List<Account> getAcc() {
		return acc;
	}
	public void setAcc(List<Account> acc) {
		this.acc = acc;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (acc.contains(arg0)) 
			System.out.println(arg1.toString());
	}
}
