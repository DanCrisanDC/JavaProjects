package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.ClientPhoneValidator;
import bll.ClientAgeValidator;
import bll.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDao;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientPhoneValidator());
		validators.add(new ClientAgeValidator());
		clientDao = new ClientDAO();
	}

	public Client findClientById(int id) {
		Client c = clientDao.findById(id);
		if (c == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return c;
	}

	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return clientDao.insert(client);
	}
}