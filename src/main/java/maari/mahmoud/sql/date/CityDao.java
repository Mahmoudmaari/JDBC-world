package maari.mahmoud.sql.date;

import java.util.List;

import maari.mahmoud.sql.models.City;

public interface CityDao {
	void printCities();

	City findById(int id);

	List<City> findByCode(String code);

	List<City> findByName(String name);

	List<City> findAll();

	City add(City city);

	City update(City city);

	City delete(City city);

}
