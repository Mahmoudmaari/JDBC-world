package maari.mahmoud.sql.date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import maari.mahmoud.sql.models.City;

public class CityDaoMpl implements CityDao {
	@Override
	public void printCities() {

		try (Connection conn = DateB.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT Name From city");) {

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public City findById(int id) {
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatement(conn, id);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				return createCityFromResultset(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatement(Connection conn, int number) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE ID =?");
		statement.setInt(1, number);
		return statement;
	}

	private PreparedStatement createPreparedStatement(Connection conn, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE CountryCode  = ?");
		statement.setString(1, name);
		return statement;
	}

	private City createCityFromResultset(ResultSet rs) throws SQLException {
		return new City(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	}

	private List<City> createCityFromResultsetList(ResultSet rs) throws SQLException {
		List<City> result = new ArrayList<>();

		while (rs.next()) {
			City c = new City(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			result.add(c);
		}

		return result;
	}

	@Override
	public List<City> findByCode(String code) {
		List<City> result;
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatement(conn, code);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				result = createCityFromResultsetList(rs);
				return result;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<City> findByName(String name) {
		List<City> result;
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatementName(conn, "%" + name + "%");
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				result = createCityFromResultsetList(rs);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatementName(Connection conn, String Cityname) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE Name like ?");
		statement.setString(1, Cityname);
		return statement;
	}

	@Override
	public List<City> findAll() {
		
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatementAll(conn);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				return createCityFromResultsetList(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatementAll(Connection conn) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city");
		return statement;
	}

	@Override
	public City add(City city) {
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatementadd(conn, city);) {
			 statement.executeUpdate();
			return findById(city.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatementadd(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO city VALUES (?,?,?,?,?)");
		statement.setInt(1, city.getId());
		statement.setString(2, city.getName());
		statement.setString(3, city.getCountryCode());
		statement.setString(4, city.getDistrict());
		statement.setInt(5, city.getPopulation());
		return statement;
	}

	@Override
	public City update(City city) {
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatementupdate(conn, city);) {
			 statement.executeUpdate();
			return findById(city.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatementupdate(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("UPDATE city SET Name = ? ,population = ? WHERE id = ?");
		statement.setString(1, city.getName());
		statement.setInt(2, city.getPopulation());
		statement.setInt(3, city.getId());
		return statement;
	} 

	@Override
	public City delete(City city) {
		try (Connection conn = DateB.getConnection();
				PreparedStatement statement = createPreparedStatementDelet(conn, city);) {
			 statement.executeUpdate();
			return findById(city.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement createPreparedStatementDelet(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("DELETE FROM city WHERE id = ?");
		statement.setInt(1, city.getId());
		return statement;
	}

}
