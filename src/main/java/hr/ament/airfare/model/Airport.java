package hr.ament.airfare.model;

import com.opencsv.bean.CsvBindByName;

public class Airport extends CsvAirport{

    @CsvBindByName(column = "iata")
    private String iata;

    @CsvBindByName
    private String airportName;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

}
