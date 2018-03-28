package BT;

public interface BankProc {

	/*
	 * This method checks if a person p is registered in the bank,
	 * and if not, adds it. Otherwise, it prints a message saying 
	 * that said person is already registered.
	 * @pre p!=null
	 * @pre !hm.containsValue(p) 
	 * @pre !per.contains(p)
	 * @post per.contains(p)
	 * @post hm.containsValue(p) 
	 */
	void addPerson(Person p);
	
	/*
	 * This method checks if a person p is registered in the bank
	 * and if so, it deletes it. Otherwise, it prints a message
	 * saying the person does not exist.
	 * @pre p!=null
	 * @pre hm.containsValue(p) 
	 * @pre per.contains(p)
	 * @post !per.contains(p)
	 */
	void removePerson(Person p);
	
	/*
	 * This method checks if a person p is registered in the bank
	 * and if it has an account, if not the account is added. Otherwise, it prints a message
	 * saying the person does not exist or the account already exists.
	 * @pre p!=null
	 * @pre acc!=null
	 * @pre hm.containsValue(p) 
	 * @pre !p.getAcc().contains(acc)
	 * @post p.getAcc().contains(acc)
	 */
	void addAccount(Account acc, Person p);
	
	/*
	 * This method checks if a person p is registered in the bank
	 * and if it has an account, if so the account is deleted. Otherwise, it prints a message
	 * saying the person does not exist or the account does not exist.
	 * @pre p!=null
	 * @pre acc!=null
	 * @pre hm.containsValue(p) 
	 * @pre p.getAcc().contains(acc)
	 * @post !p.getAcc().contains(acc)
	 */
	void removeAccount(Account acc, Person p);
}
