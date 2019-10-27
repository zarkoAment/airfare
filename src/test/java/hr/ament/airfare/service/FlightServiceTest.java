package hr.ament.airfare.service;

import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.QueryParams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static hr.ament.airfare.utils.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @Mock
    public FlightService flightService;

    private Date dateDeparture = new Date(System.currentTimeMillis());
    private Date dateReturn = new Date(dateDeparture.getTime() + FIVE_DAYS);
    private List<Flight> flights = new ArrayList<>();
    private QueryParams queryParams = new QueryParams();

    @Before
    public void setUp() {
        initFlightService();
    }

    @Test
    public void should_find_flights() {
        //build
        initQueryParams();

        //operate
        final List<Flight> flights = flightService.getFlights(queryParams);

        //check
        Assert.assertSame(DEPARTURE_AIRPORT, flights.get(0).getDepartureAirport());
    }

    @Test
    public void should_not_return_flights_when_query_param_is_null(){
        //build

        //operate
        final List<Flight> flights = flightService.getFlights(null);

        //check
        Assert.assertTrue(flights.isEmpty());
    }

    private void initFlightService() {
        Flight flight = new Flight();
        flight.setReturnAirport(RETURN_AIRPORT);
        flight.setDepartureAirport(DEPARTURE_AIRPORT);
        flight.setDateDeparture(dateDeparture);
        flight.setReturnDate(dateReturn);
        flight.setNumberOfTransfersDeparture(TWO_TRANSFERS);
        flight.setNumberOfTransfersArrival(ONE_TRANSFER);
        flight.setCurrency(CURRENCY_HRK);
        flight.setSumOfPrice(TEST_SUM_PRICE);

        flights.add(flight);

        when(flightService.getFlights(any(QueryParams.class))).thenReturn(flights);
    }

    private void initQueryParams() {
        queryParams = new QueryParams(DEPARTURE_AIRPORT,
                RETURN_AIRPORT,
                dateDeparture,
                new Date(dateDeparture.getTime() + FIVE_DAYS),
                1,
                CURRENCY_HRK );
    }
}
