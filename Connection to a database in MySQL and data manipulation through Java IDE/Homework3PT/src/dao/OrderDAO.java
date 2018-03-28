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
import model.Order;
import model.Product;

public class OrderDAO {
	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementint = "INSERT INTO `order` (quantity,idClient,idProduct) VALUES (?,?,?)";
	private final static String findStatementint = "SELECT * FROM `order` where idOrder = ?";
	private final static String findCStatementint = "SELECT * FROM `order` where idClient = ?";
	private final static String findPStatementint = "SELECT * FROM `order` where idProduct = ?";
	private static final String updateStatementint = "UPDATE `order` SET quantity=?,idClient=?,idProduct=? WHERE idOrder=?";
	private final static String findAllStatementint = "SELECT * FROM `order`";
	private static final String deleteStatementint = "DELETE FROM `order` WHERE idOrder=?";

	public Order findById(int orderId) {
		Order toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementint);
			findStatement.setInt(1, orderId);
			rs = findStatement.executeQuery();

			int quantity;
			int idClient;
			int idProduct;
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				idClient = rs.getInt("idClient");
				idProduct = rs.getInt("idProduct");
			} else {
				return null;
			}
			toReturn = new Order(orderId, quantity, idClient, idProduct);
		} catch (SQLException e) {
			System.out.println("Order couldn't be found.");
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public Order findByIdC(int clientId) {
		Order toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findCStatementint);
			findStatement.setInt(1, clientId);
			rs = findStatement.executeQuery();

			int quantity;
			int idClient;
			int idProduct;
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				idClient = rs.getInt("idClient");
				idProduct = rs.getInt("idProduct");
			} else {
				return null;
			}
			toReturn = new Order(clientId, quantity, idClient, idProduct);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public Order findByIdP(int productId) {
		Order toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findPStatementint);
			findStatement.setInt(1, productId);
			rs = findStatement.executeQuery();

			int quantity;
			int idClient;
			int idProduct;
			if (rs.next()) {
				quantity = rs.getInt("quantity");
				idClient = rs.getInt("idClient");
				idProduct = rs.getInt("idProduct");
			} else {
				return null;
			}
			toReturn = new Order(productId, quantity, idClient, idProduct);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public List<Order> findAll() {
		List<Order> toReturn = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementint);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id;
				int quantity;
				int idClient;
				int idProduct;
				id = rs.getInt("idOrder");
				quantity = rs.getInt("quantity");
				idClient = rs.getInt("idClient");
				idProduct = rs.getInt("idProduct");
				toReturn.add(new Order(id, quantity, idClient, idProduct));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public Order update(int id, int quantity, int idClient, int idProduct) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		Order o1 = findById(id);
		try {
			if (o1 != null) {
				updateStatement = dbConnection.prepareStatement(updateStatementint, Statement.RETURN_GENERATED_KEYS);
				updateStatement.setInt(1, quantity);
				updateStatement.setInt(2, idClient);
				updateStatement.setInt(3, idProduct);
				updateStatement.setInt(4, id);
				updateStatement.executeUpdate();
			} else {
				System.out.println("The order does not exist.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return o1;
	}

	public void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		Order o1 = findById(id);
		try {
			if (o1 != null) {
				deleteStatement = dbConnection.prepareStatement(deleteStatementint, Statement.RETURN_GENERATED_KEYS);
				deleteStatement.setInt(1, id);
				deleteStatement.executeUpdate();
			} else {
				System.out.println("The order requested to be deleted does not exist.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public int insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			ProductDAO pdao = new ProductDAO();
			Product p = pdao.findById(order.getIdProduct());
			if (order.getQuantity() > p.getQuantity() && p != null) {
				System.out.println("Only " + p.getQuantity() + " pieces left in stock.");
			} else {
				p.setQuantity(p.getQuantity() - order.getQuantity());
				pdao.update(p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getProducer());
				insertStatement = dbConnection.prepareStatement(insertStatementint, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setInt(1, order.getQuantity());
				insertStatement.setInt(2, order.getIdClient());
				insertStatement.setInt(3, order.getIdProduct());
				insertStatement.executeUpdate();
				ResultSet rs = insertStatement.getGeneratedKeys();
				if (rs.next()) {
					insertedId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
