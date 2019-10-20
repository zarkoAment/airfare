package hr.ament.airfare.model;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDto {

    private int id;
    private String departureAirport;
    private String arrivalAirport;
    private Date dateArrival;
    private Date dateDeparture;
    private int numberOfTransfersArrival;
    private int numberOfTransfersDeparture;
    private int numberOfPassangers;
    private String currency;
    private String sumOfPrice;

    public FlightDto(int numberOfPassangers, String currency, String sumOfPrice){
        this.numberOfPassangers = numberOfPassangers;
        this.currency = currency;
        this.sumOfPrice = sumOfPrice;
    }
}
