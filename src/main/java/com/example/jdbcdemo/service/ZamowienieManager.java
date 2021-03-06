package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Zamowienie;

public class ZamowienieManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableZamowienie = "CREATE TABLE Zamowienie (id bigint GENERATED BY DEFAULT AS IDENTITY, customer varchar(30), amount int, idPick bigint)";

	private PreparedStatement addZamowienieStmt;
	private PreparedStatement deleteAllZamowieniesStmt;
	private PreparedStatement getAllZamowieniesStmt;
	private PreparedStatement addPickStmt;
	private PreparedStatement deletePickStmt;
	private PreparedStatement getAllZamWherePickStmt;
	private PreparedStatement selectIDStmt;

	private Statement statement;

	public ZamowienieManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Zamowienie".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableZamowienie);

			addZamowienieStmt = connection
					.prepareStatement("INSERT INTO Zamowienie (customer, amount) VALUES (?, ?)");
			deleteAllZamowieniesStmt = connection
					.prepareStatement("DELETE FROM Zamowienie");
			getAllZamowieniesStmt = connection
					.prepareStatement("SELECT id, customer, amount, idPick FROM Zamowienie");
			addPickStmt = connection
					.prepareStatement("UPDATE Zamowienie SET idPick=? WHERE id=?");
			deletePickStmt = connection
					.prepareStatement("UPDATE Zamowienie SET idPick=NULL WHERE id=?");
			getAllZamWherePickStmt = connection
					.prepareStatement("SELECT id, customer, amount, idPick FROM Zamowienie WHERE idPick=?");
			selectIDStmt = connection
					.prepareStatement("SELECT id, customer, amount, idPick FROM Zamowienie WHERE id=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearZamowienies() {
		try {
			deleteAllZamowieniesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addZamowienies(Zamowienie zamowienie) {
		int count = 0;
		try {
			addZamowienieStmt.setString(1, zamowienie.getCustomer());
			addZamowienieStmt.setInt(2, zamowienie.getAmount());

			count = addZamowienieStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Zamowienie> getAllZamowienies() {
		List<Zamowienie> zamowienies = new ArrayList<Zamowienie>();

		try {
			ResultSet rs = getAllZamowieniesStmt.executeQuery();

			while (rs.next()) {
				Zamowienie o = new Zamowienie();
				o.setId(rs.getInt("id"));
				o.setCustomer(rs.getString("customer"));
				o.setIdPick(rs.getInt("idPick"));
				o.setAmount(rs.getInt("amount"));
				zamowienies.add(o);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zamowienies;
	}

	public Zamowienie SelectID(long id) {

		Zamowienie test = null;

		try {
			selectIDStmt.setLong(1, id);

			ResultSet rs = selectIDStmt.executeQuery();

			test = new Zamowienie();
			test.setId(rs.getInt("id"));
			test.setCustomer(rs.getString("customer"));
			test.setAmount(rs.getInt("amount"));
			test.setIdPick(rs.getInt("idPick"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return test;

	}

	public int addPick(int idPick, int id) {
		int count = 0;

		try {
			addPickStmt.setInt(1, idPick);
			addPickStmt.setInt(2, id);

			count = addPickStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public int deletePick(int id) {
		int count = 0;

		try {
			deletePickStmt.setLong(1, id);
			count = deletePickStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
	
	public List<Zamowienie> getAllZamWherePick(int id){
        List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
        
        try {
        		getAllZamWherePickStmt.setInt(1, id);
                ResultSet rs = getAllZamWherePickStmt.executeQuery();
                
                while(rs.next()){
                        Zamowienie z = new Zamowienie();
                        z.setId(rs.getInt("id"));
                        z.setCustomer(rs.getString("customer"));
                        z.setAmount(rs.getInt("amount"));
                        z.setIdPick(rs.getInt("idPick"));
                        zamowienia.add(z);
                }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return zamowienia;
}

}
