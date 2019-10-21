package hr.ament.airfare.repository;

import hr.ament.airfare.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDao extends CrudRepository<Flight, Integer>, FlightSearchDao {

}
