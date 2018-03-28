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

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductDAO {

	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO product (name,price,quantity,producer) VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where idProduct = ?";
	private static final String updateStatementString = "UPDATE product SET name=?,price=?,quantity=?,producer=? WHERE idProduct=?";
	private final static String findAllStatementString = "SELECT * FROM product";
	private static final String deleteStatementString = "DELETE FROM product WHERE idProduct=?";

	public Product findById(int productId) {
		Product toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, productId);
			rs = findStatement.executeQuery();

			String name = null;
			double price = 0.0;
			int quantity = 0;
			String producer = null;
			if (rs.next()) {
				name = rs.getString("name");
				price = rs.getDouble("price");
				quantity = rs.getInt("quantity");
				producer = rs.getString("producer");
			} else {
				return null;
			}
			toReturn = new Product(productId, name, price, quantity, producer);
		} catch (SQLException e) {
			System.out.println("Product couldn't be found.");
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public List<Product> findAll() {
		List<Product> toReturn = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id = 0;
				String name = null;
				double price = 0.0;
				int quantity = 0;
				String producer = null;
				id = rs.getInt("idProduct");
				name = rs.getString("name");
				price = rs.getDouble("price");
				quantity = rs.getInt("quantity");
				producer = rs.getString("producer");
				toReturn.add(new Product(id, name, price, quantity, producer));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public Product update(int id, String name, double price, int quantity, String producer) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		Product p1 = findById(id);
		try {
			if (p1 != null) {
				updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
				updateStatement.setString(1, name);
				updateStatement.setDouble(2, price);
				updateStatement.setInt(3, quantity);
				updateStatement.setString(4, producer);
				updateStatement.setInt(5, id);
				updateStatement.executeUpdate();
			} else {
				System.out.println("The product does not exist.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return p1;
	}

	public void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		Product p1 = findById(id);
		try {
			OrderDAO odao = new OrderDAO();
			Order o = odao.findByIdP(id);
			if (o != null) {
				System.out.println("The product is currently in an order and cannot be deleted.");
			} else {
				if (p1 != null) {
					deleteStatement = dbConnection.prepareStatement(deleteStatementString,
							Statement.RETURN_GENERATED_KEYS);
					deleteStatement.setInt(1, id);
					deleteStatement.executeUpdate();
				} else {
					System.out.println("The product requested to be deleted does not exist.");
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public int insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, product.getName());
			insertStatement.setDouble(2, product.getPrice());
			insertStatement.setInt(3, product.getQuantity());
			insertStatement.setString(4, product.getProducer());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}