package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Order;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (name,age,address,phone) VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM client where idClient = ?";
	private static final String updateStatementString = "UPDATE client SET name=?,age=?,address=?,phone=? WHERE idClient=?";
	private final static String findAllStatementString = "SELECT * FROM client";
	private static final String deleteStatementString = "DELETE FROM client WHERE idClient=?";

	public Client findById(int clientId) {
		Client toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, clientId);
			rs = findStatement.executeQuery();

			String name = null;
			int age = 0;
			String address = null;
			String phone = null;
			if (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				address = rs.getString("address");
				phone = rs.getString("phone");
			} else {
				return null;
			}
			toReturn = new Client(clientId, name, age, address, phone);
		} catch (SQLException e) {
			System.out.println("Client couldn't be found.");
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public List<Client> findAll() {
		List<Client> toReturn = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id = 0;
				String name = null;
				int age = 0;
				String address = null;
				String phone = null;
				id = rs.getInt("idClient");
				name = rs.getString("name");
				age = rs.getInt("age");
				address = rs.getString("address");
				phone = rs.getString("phone");
				toReturn.add(new Client(id, name, age, address, phone));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public Client update(int id, String name, int age, String address, String phone) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		Client c1 = findById(id);
		try {
			if (c1 != null) {
				updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
				updateStatement.setString(1, name);
				updateStatement.setInt(2, age);
				updateStatement.setString(3, address);
				updateStatement.setString(4, phone);
				updateStatement.setInt(5, id);
				updateStatement.executeUpdate();
			} else {
				System.out.println("The client does not exist.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return c1;
	}

	public void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		Client c1 = findById(id);
		try {
			OrderDAO odao = new OrderDAO();
			Order o = odao.findByIdC(id);
			if (o != null) {
				System.out.println("The client currently has an order in place and cannot be deleted.");
			} else {
				if (c1 != null) {
					deleteStatement = dbConnection.prepareStatement(deleteStatementString,
							Statement.RETURN_GENERATED_KEYS);
					deleteStatement.setInt(1, id);
					deleteStatement.executeUpdate();
				} else {
					System.out.println("The client requested to be deleted does not exist.");
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setInt(2, client.getAge());
			insertStatement.setString(3, client.getAddress());
			insertStatement.setString(4, client.getPhone());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}