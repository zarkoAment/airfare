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


    public QueryParams() {

    }

    public QueryParams(String departureAirport, String returnAirport, Date dateDeparture, Date returnDate, int numberOfPassangers, String currency) {
        this.departureAirport = departureAirport;
        this.returnAirport = returnAirport;
        this.returnDate = returnDate;
        this.dateDeparture = dateDeparture;
        this.numberOfPassangers = numberOfPassangers;
        this.currency = currency;
    }
}
