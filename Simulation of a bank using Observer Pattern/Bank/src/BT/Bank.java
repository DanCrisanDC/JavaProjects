package BT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Bank implements BankProc, Serializable {

	private static final long serialVersionUID =1113799434508676095L;
	private HashMap<String,Person> hm = new HashMap<String,Person>();
	private List<Person> per = new ArrayList<Person>();
	
	public Bank() {
		
	}
	
	public HashMap<String, Person> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, Person> hm) {
		this.hm = hm;
	}

	public List<Person> getPer() {
		return per;
	}

	public void setPer(List<Person> per) {
		this.per = per;
	}
	
	public boolean wellFormed() {
		for (Entry<String,Person> per : hm.entrySet()) {
			if(per.equals(null))
				return false;
			if(per.getKey().equals(null))
				return false;
		}
		return true;
	}

	@Override
	public void addPerson(Person p) {
		assert wellFormed();
		assert p!=null;
		assert !hm.containsValue(p) : "Person already exists!";
		assert !per.contains(p) : "Person already exists!";
		hm.put(p.getName(), p);
		per.add(p);
		assert per.contains(p) : "Person hasn't been added!";
		assert hm.containsValue(p) : "Person hasn't been added!";
		assert wellFormed();
	}

	@Override
	public void removePerson(Person p) {
		assert wellFormed();
		assert p!=null;
		assert hm.containsValue(p) : "Person does not exist!";
		hm.remove(p.getName());
		per.remove(p);	
		assert !per.contains(p) : "Person hasn't been removed!";
		assert wellFormed();
	}

	@Override
	public void addAccount(Account acc, Person p) {
		assert wellFormed();
		assert p!=null;
		assert acc!=null;
		assert hm.containsValue(p) : "Person does not exist!";
		assert !p.getAcc().contains(acc) : "Account already exists!";
		p.getAcc().add(acc);	
		acc.addObserver(p);
		assert p.getAcc().contains(acc) : "Account hasn't been added!";
		assert wellFormed();
	}

	@Override
	public void removeAccount(Account acc, Person p) {
		assert wellFormed();
		assert p!=null;
		assert acc!=null;
		assert hm.containsValue(p) : "Person does not exist!";
		assert p.getAcc().contains(acc) : "Account does not exist!";
		p.getAcc().remove(acc);
		acc.deleteObserver(p);
		assert !p.getAcc().contains(acc) : "Account hasn't been removed!";
		assert wellFormed();
	}
	
	public Person findPerson(String name, String address) {
		for (String p : hm.keySet()) {
			if (p.equals(name) && hm.get(p).getAddress().equals(address))
				return hm.get(p);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void deserialization() {
		try {
	        FileInputStream fileIn = new FileInputStream("bankSer.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        hm =  ((HashMap<String, Person>) in.readObject());
	        this.setHm(hm);
	        in.close();
	        fileIn.close();
	    } catch(IOException i) {
	        i.printStackTrace();
	    } catch(ClassNotFoundException c) {
	        System.out.println("Bank class not found");
	        c.printStackTrace();
	        return;
	    }
	}
	
	public void serialization() {
		try {
			FileOutputStream fileOut = new FileOutputStream("bankSer.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(hm);
			out.close();
			fileOut.close();
	    } catch(IOException i) {
	        i.printStackTrace();
	    }
	}
}
