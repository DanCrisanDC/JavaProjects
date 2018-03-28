package bll;

import model.Client;

public class ClientPhoneValidator implements Validator<Client>{
	
	public void validate(Client t) {
		if (t.getPhone().indexOf("0") != 0 || t.getPhone().indexOf("7") != 1) {
			throw new IllegalArgumentException("The Client Phone format is not respected!");
		}

	}
}
