package hr.ament.airfare.controller;

import com.opencsv.bean.*;
import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.Airport;
import hr.ament.airfare.model.QueryParams;
import hr.ament.airfare.repository.FlightDao;
import hr.ament.airfare.service.FlightService;
import hr.ament.airfare.utils.CsvTransfer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightDao flightDao;

    @GetMapping("/")
    public String greetingForm(Model model) throws Exception {
        model.addAttribute("queryParams", new QueryParams());
        model.addAttribute("airports", airportBuilder(Paths.get(
                ClassLoader.getSystemResource("csv/airport.csv").toURI()),Airport.class));
        return "index";
    }

    @PostMapping("/query")
    public String queryFlights(@ModelAttribute QueryParams queryParams, Model model) throws Exception {
        model.addAttribute("flights",
                flightDao.findFlights(queryParams)
                        .orElseGet(() -> fetchAndSave(queryParams)));
        model.addAttribute("airports", airportBuilder(Paths.get(
                ClassLoader.getSystemResource("csv/airport.csv").toURI()),Airport.class));
        return "index";
    }

    private List<Flight> fetchAndSave(QueryParams queryParams) {
        List<Flight> flights = flightService.getFlights(queryParams);
        flightDao.saveAll(flights);
        return flights;
    }

    public List<Airport> airportBuilder(Path path, Class<Airport> clazz) throws Exception {
        CsvTransfer csvTransfer = new CsvTransfer();
        MappingStrategy<Airport> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(clazz);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean<Airport> cb = new CsvToBeanBuilder<Airport>(reader)
                .withType(clazz)
                .withSeparator(',')
                .withMappingStrategy(ms)
                .build();

        csvTransfer.setCsvList(cb.parse());
        reader.close();

        return csvTransfer.getCsvList();
    }
}
