package hr.ament.airfare.service;

import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.QueryParams;
import hr.ament.swagairfare.client.api.FlightOffersApi;
import hr.ament.swagairfare.client.model.FlightOffers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightOffersApi flightOffersApi;

    public FlightService(FlightOffersApi flightOffersApi) {
        this.flightOffersApi = flightOffersApi;
    }

    public List<Flight> getFlights(QueryParams queryParams) {
        FlightOffers flightOffers = flightOffersApi.getFlightOffers(
                queryParams.getDepartureAirport(),
                queryParams.getReturnAirport(),
                DateTimeUtils.toLocalDate(queryParams.getDateDeparture()),
                DateTimeUtils.toLocalDate(queryParams.getReturnDate()),
                null,
                null,
                queryParams.getNumberOfPassangers(),
                0,
                0,
                0,
                null,
                null,
                null,
                false,
                queryParams.getCurrency(),
                null,
                null);

        List<Flight> fareData = new ArrayList<>();
        flightOffers.getData().forEach(
                data -> data.getOfferItems().forEach(
                        item -> FlightOfferProcessor.offerProcessor(item, fareData, queryParams.getCurrency(), queryParams.getNumberOfPassangers())
                )
        );

        return fareData;
    }
}
