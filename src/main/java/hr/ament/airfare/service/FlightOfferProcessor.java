package hr.ament.airfare.service;

import hr.ament.airfare.domain.Flight;
import hr.ament.swagairfare.client.model.FlightSegment;
import hr.ament.swagairfare.client.model.OfferItem;
import hr.ament.swagairfare.client.model.Segment;
import hr.ament.swagairfare.client.model.Service;
import org.threeten.bp.DateTimeUtils;

import java.util.List;

public class FlightOfferProcessor {

    public static void offerProcessor(OfferItem offerItem, List<Flight> flightData, String currency, int passengerCount) {
        Flight fare = new Flight(passengerCount, currency, offerItem.getPrice().getTotal());
        flightData.add(fare);
        offerItem.getServices().forEach(s -> serviceProcessor(s, fare));
    }

    private static void serviceProcessor(Service service, Flight fare) {
        List<Segment> segments = service.getSegments();
        //staviti svaki drugi, postaviti presjedanja u odlasku
        fare.setNumberOfTransfersArrival(segments.size());
        segmentProcessor(segments, fare);
    }

    private static void segmentProcessor(List<Segment> segments, Flight fare) {
        if (segments != null && !segments.isEmpty()) {
            FlightSegment departingSegment = segments.get(0).getFlightSegment();
            FlightSegment arrivalSegment = segments.get(segments.size()-1).getFlightSegment();
            fare.setDepartureAirport(departingSegment.getDeparture().getIataCode());
            fare.setArrivalAirport(arrivalSegment.getArrival().getIataCode());
            fare.setDateDeparture(DateTimeUtils.toDate(departingSegment.getDeparture().getAt().toInstant()));
            fare.setDateArrival(DateTimeUtils.toDate(arrivalSegment.getArrival().getAt().toInstant()));
        }
    }
}
