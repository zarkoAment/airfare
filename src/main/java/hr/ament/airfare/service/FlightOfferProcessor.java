package hr.ament.airfare.service;

import hr.ament.airfare.domain.Flight;
import hr.ament.swagairfare.client.model.FlightSegment;
import hr.ament.swagairfare.client.model.OfferItem;
import hr.ament.swagairfare.client.model.Segment;
import hr.ament.swagairfare.client.model.Service;
import org.threeten.bp.DateTimeUtils;

import java.util.List;

import static hr.ament.airfare.utils.Constants.*;

public class FlightOfferProcessor {

    public static void offerProcessor(OfferItem offerItem, List<Flight> flightData, String currency, int passengerCount) {
        Flight fare = new Flight(passengerCount, currency, offerItem.getPrice().getTotal());
        flightData.add(fare);

        departureServiceProcessor(offerItem.getServices().get(DEPARTURE), fare);
        returnServiceProcessor(offerItem.getServices().get(RETURN), fare);
    }

    private static void returnServiceProcessor(Service service, Flight fare) {
        List<Segment> segments = service.getSegments();

        fare.setNumberOfTransfersArrival(segments.size()-1);
        returnSegmentProcessor(segments, fare);
    }

    private static void departureServiceProcessor(Service service, Flight fare) {
        List<Segment> segments = service.getSegments();
        fare.setNumberOfTransfersDeparture(segments.size()-1);

        departureSegmentProcessor(segments, fare);
    }

    private static void returnSegmentProcessor(List<Segment> segments, Flight fare) {
        if (segments != null && !segments.isEmpty()) {
            FlightSegment returnSegment = segments.get(FIRST_SEGMENT).getFlightSegment();

            fare.setReturnAirport(returnSegment.getDeparture().getIataCode());
            fare.setReturnDate(DateTimeUtils.toDate(returnSegment.getDeparture().getAt().toInstant()));
        }
    }

    private static void departureSegmentProcessor(List<Segment> segments, Flight fare) {
        if (segments != null && !segments.isEmpty()) {
            FlightSegment departureSegment = segments.get(FIRST_SEGMENT).getFlightSegment();
            fare.setDepartureAirport(departureSegment.getDeparture().getIataCode());
            fare.setDateDeparture(DateTimeUtils.toDate(departureSegment.getArrival().getAt().toInstant()));
        }
    }
}
