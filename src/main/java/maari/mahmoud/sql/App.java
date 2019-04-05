package maari.mahmoud.sql;

import java.sql.SQLException;

import maari.mahmoud.sql.date.CityDao;
import maari.mahmoud.sql.date.CityDaoMpl;
import maari.mahmoud.sql.models.City;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SQLException {
		CityDao dao = new CityDaoMpl();
		City city = new City(5000, "Edlib", "PSE", "north syria", 50000);
		City cityup = new City(5000, "Halab", "kdkd", "north kdkd", 2000000);
		dao.add(city);
		System.out.println(dao.findById(5000));
		dao.update(cityup);
		System.out.println(dao.findById(5000));
		dao.delete(city);
		System.out.println(dao.findById(5000));
		System.out.println(dao.findByCode("swe"));
		
	}
}
