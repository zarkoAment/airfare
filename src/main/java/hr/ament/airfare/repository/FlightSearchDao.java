package hr.ament.airfare.repository;

import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.QueryParams;

import java.util.List;
import java.util.Optional;

public interface FlightSearchDao {
    Optional<List<Flight>> findFlights (QueryParams queryParams);
}
