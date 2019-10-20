package hr.ament.airfare.model;

import lombok.Data;

import java.sql.Date;

@Data
public class QueryParams {

    private String departureAirport;
    private String arrivalAirport;
    private Date dateArrival;
    private Date dateDeparture;
    private int numberOfPassangers;
    private String currency;



}
