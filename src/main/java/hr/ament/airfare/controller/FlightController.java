package hr.ament.airfare.controller;

import hr.ament.airfare.domain.Flight;
import hr.ament.airfare.model.QueryParams;
import hr.ament.airfare.repository.FlightDao;
import hr.ament.airfare.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightDao flightDao;

    @GetMapping("/query")
    public String greetingForm(Model model) {
        model.addAttribute("queryParams", new QueryParams());
        return "index";
    }

    @PostMapping("/query")
    public String queryFlights(@ModelAttribute QueryParams queryParams, Model model) {
        model.addAttribute(
                "flights",
                flightDao.findFlights(queryParams)
                        .orElseGet(() -> fetchAndSave(queryParams)));
        return "index";
    }

    private List<Flight> fetchAndSave(QueryParams queryParams) {
        List<Flight> flights = flightService.getFlights(queryParams);
        flightDao.saveAll(flights);
        return flights;
    }

    private void saveFlightData(List<Flight> flights) {
        flightDao.saveAll(flights);
        System.out.println(flightDao.findAll());
    }


}
