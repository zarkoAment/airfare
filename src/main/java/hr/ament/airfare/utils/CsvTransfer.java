package hr.ament.airfare.utils;

import hr.ament.airfare.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class CsvTransfer {

    private List<String[]> csvStringList;

    private List<Airport> csvList;

    public CsvTransfer() {}

    public List<String[]> getCsvStringList() {
        if (csvStringList != null) return csvStringList;
        return new ArrayList<String[]>();
    }

    public void addLine(String[] line) {
        if (this.csvList == null) this.csvStringList = new ArrayList<>();
        this.csvStringList.add(line);
    }

    public void setCsvStringList(List<String[]> csvStringList) {
        this.csvStringList = csvStringList;
    }

    public void setCsvList(List<Airport> csvList) {
        this.csvList = csvList;
    }

    public List<Airport> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<Airport>();
    }
}
