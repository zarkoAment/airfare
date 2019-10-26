package hr.ament.airfare.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "FLIGHT_TABLE")
@Data
public class Flight {

    @Column (name = "ID")
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "FLIGHT_DEPARTURE_AIRPORT", length = 5)
    private String departureAirport;

    @Column (name = "FLIGHT_ARRIVAL_AIRPORT", length = 5)
    private String returnAirport;

    @Column (name = "FLIGHT_RETURN_DATE")
    private Date returnDate;

    @Column (name = "FLIGHT_DATE_DEPARTURE")
    private Date dateDeparture;

    @Column (name = "FLIGHT_NUMBER_OF_TRANSFERS_ARRIVAL", length = 2)
    private int numberOfTransfersArrival;

    @Column (name = "FLIGHT_NUMBER_OF_TRANSFER_DEPARTURE", length = 2)
    private int numberOfTransfersDeparture;

    @Column (name = "FLIGHT_NUMBER_OF_PASSANGERS", length = 500)
    private int numberOfPassangers;

    @Column (name = "FLIGHT_CURRENCY", length = 5)
    private String currency;

    @Column (name = "FLIGHT_SUM_OF_PRICE", length = 10)
    private String sumOfPrice;

    public Flight(int numberOfPassangers, String currency, String sumOfPrice){
        this.numberOfPassangers = numberOfPassangers;
        this.currency = currency;
        this.sumOfPrice = sumOfPrice;
    }

    public Flight() {

    }

}
