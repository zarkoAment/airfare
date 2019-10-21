package hr.ament.airfare.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.domain.QFlight;
import hr.ament.airfare.model.QueryParams;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class FlightSearchDaoImpl implements FlightSearchDao {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<List<Flight>> findFlights(QueryParams queryParams) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlight flight = QFlight.flight;

        Optional<List<Flight>> flights = Optional.of(queryFactory.selectFrom(flight)
                .where(flight.departureAirport.eq(queryParams.getDepartureAirport())
                        .and(flight.arrivalAirport.eq(queryParams.getArrivalAirport()))
                        .and(flight.dateDeparture.eq(queryParams.getDateDeparture()))
                        .and(flight.dateArrival.eq(queryParams.getDateArrival()))
                        .and(flight.currency.eq(queryParams.getCurrency()))
                        .and(flight.numberOfPassangers.eq(queryParams.getNumberOfPassangers())))
                .fetch());

        if (flights != null  )


        //dodati if ovdje i vratiti optional.empty kada je query prazan
        try {
            return Optional.of(queryFactory.selectFrom(flight)
                    .where(flight.departureAirport.eq(queryParams.getDepartureAirport())
                            .and(flight.arrivalAirport.eq(queryParams.getArrivalAirport()))
                            .and(flight.dateDeparture.eq(queryParams.getDateDeparture()))
                            .and(flight.dateArrival.eq(queryParams.getDateArrival()))
                            .and(flight.currency.eq(queryParams.getCurrency()))
                            .and(flight.numberOfPassangers.eq(queryParams.getNumberOfPassangers())))
                    .fetch());
            }

        catch (EntityNotFoundException exception) {
            return Optional.empty();
        }

    }
}
