package hr.ament.airfare.model;

import lombok.Data;

import java.sql.Date;

@Data
public class QueryParams {

    private String departureAirport;
    private String returnAirport;
    private Date returnDate;
    private Date dateDeparture;
    private int numberOfPassangers;
    private String currency;

}


