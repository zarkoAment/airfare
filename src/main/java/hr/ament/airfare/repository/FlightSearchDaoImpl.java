package hr.ament.airfare.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.domain.QFlight;
import hr.ament.airfare.model.QueryParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FlightSearchDaoImpl implements FlightSearchDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<List<Flight>> findFlights(QueryParams queryParams) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlight flight = QFlight.flight;

        Date departureDateAfter = new Date(queryParams.getDateDeparture().getTime());
        Date departureDateBefore = addDay(departureDateAfter);
        Date arrivalDateAfter = new Date(queryParams.getReturnDate().getTime());
        Date arrivalDateBefore = addDay(arrivalDateAfter);

        List<Flight> flights = queryFactory.selectFrom(flight)
                .where(flight.departureAirport.eq(queryParams.getDepartureAirport())
                        .and(flight.returnAirport.eq(queryParams.getReturnAirport()))
                        .and(flight.dateDeparture.after(departureDateAfter))
                        .and(flight.dateDeparture.before(departureDateBefore))
                        .and(flight.returnDate.after(arrivalDateAfter))
                        .and(flight.returnDate.before(arrivalDateBefore))
                        .and(flight.currency.eq(queryParams.getCurrency()))
                        .and(flight.numberOfPassangers.eq(queryParams.getNumberOfPassangers())))
                .fetch();

        if (!flights.isEmpty()) {
            return Optional.of(flights);
        } else {
            return Optional.empty();
        }
    }

    private Date addDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
