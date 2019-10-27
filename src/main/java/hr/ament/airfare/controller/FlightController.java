package hr.ament.airfare.controller;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.Airport;
import hr.ament.airfare.model.CsvAirport;
import hr.ament.airfare.model.QueryParams;
import hr.ament.airfare.repository.FlightDao;
import hr.ament.airfare.service.FlightService;
import hr.ament.airfare.utils.CsvReaderExamples;
import hr.ament.airfare.utils.CsvTransfer;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightDao flightDao;

    @GetMapping("/")
    public String greetingForm(Model model) throws Exception {
        model.addAttribute("queryParams", new QueryParams());
        System.out.println("csv==== " + namedColumnBeanExample());
        System.out.println("readAllExample() " + readAllExample());
        System.out.println("readAll === " + readAll(Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("csv/airport.csv").toURI()))));
        return "index";
    }

    @PostMapping("/query")
    public String queryFlights(@ModelAttribute QueryParams queryParams, Model model) {
        model.addAttribute("flights",
                flightDao.findFlights(queryParams)
                        .orElseGet(() -> fetchAndSave(queryParams)));
        return "index";
    }

    private List<Flight> fetchAndSave(QueryParams queryParams) {
        List<Flight> flights = flightService.getFlights(queryParams);
        flightDao.saveAll(flights);
        return flights;
    }

    public List<CsvAirport> beanBuilderExample(Path path, Class clazz) throws Exception {
        CsvTransfer csvTransfer = new CsvTransfer();
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withSeparator(',')
                .withMappingStrategy(ms)
                .build();

        csvTransfer.setCsvList(cb.parse());
        reader.close();

        Airport airport = (Airport) csvTransfer.getCsvList().get(2);
        System.out.println("CsvAir ==== "+ airport.getAirportName());
        return csvTransfer.getCsvList();
    }

    public String namedColumnBeanExample() throws Exception {
        Path path = Paths.get(
                ClassLoader.getSystemResource("csv/airport.csv").toURI());
        return beanBuilderExample(path, Airport.class).toString();
    }

    public List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    public String readAllExample() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("csv/airport.csv").toURI()));
        return CsvReaderExamples.readAll(reader).get(2).toString();
    }
}
