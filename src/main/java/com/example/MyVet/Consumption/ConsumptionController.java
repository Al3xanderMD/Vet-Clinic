package com.example.MyVet.Consumption;

import com.example.MyVet.Appointment.Date.Status;
import com.example.MyVet.Exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/consumptions")
public class ConsumptionController {
    private final ConsumptionService consumptionService;

    @Autowired
    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Consumption> create(@RequestBody ConsumptionDTO consumptionDTO) {
        Consumption savedConsumption = consumptionService.create(consumptionDTO);
        return new ResponseEntity<>(savedConsumption, HttpStatus.CREATED);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Consumption> getAll(){
        return consumptionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Consumption getById(@PathVariable("id") String id) {
        Optional<Consumption> book = consumptionService.getById(id);
        if (book.isPresent())
            return book.get();
        else return null;
    }

    @GetMapping("/{drugId}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public double[] getByYear(
            @PathVariable("drugId") @NotNull String drugId,
            @PathVariable("year") @NotNull int year
    ) {
        List<Consumption> consumptions = consumptionService.getByYear(year, drugId);
        double[] finalList = new double[12];
        Boolean ok;
        for (int i = 0; i < 12; i++) {
            ok = false;
            for (Consumption consumption : consumptions) {
                if (consumption.getMonth() == i + 1) {
                    finalList[i] = consumption.getQuantitySold();
                    ok = true;
                }
            }
            if(!ok){
                finalList[i] = 0;
            }
        }
        return finalList;
    }

    @GetMapping("/{drugId}/")
    @ResponseStatus(HttpStatus.OK)
    public List<Pair<Integer, double[]>> getByDrug(
            @PathVariable("drugId") @NotNull String drugId
    ) {
        List<Pair<Integer, double[]>> finalList = new ArrayList<>();
        for (Integer yearI = 2020; yearI <= LocalDate.now().getYear(); yearI ++) {
            List<Consumption> consumptions = consumptionService.getByYear(yearI, drugId);
            double[] months = new double[12];
            Boolean ok;
            for (int i = 0; i < 12; i++) {
                ok = false;
                for (Consumption consumption : consumptions) {
                    if (consumption.getMonth() == i + 1) {
                        months[i] = consumption.getQuantitySold();
                        ok = true;
                    }
                }
                if(!ok){
                    months[i] = 0;
                }
            }
            Pair<Integer, double[]> pair = Pair.of(yearI, months);
            finalList.add(pair);
        }
        return finalList;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Consumption> update(@PathVariable("id") String id, @RequestBody ConsumptionDTO consumptionDTO) {
        Consumption updatedConsumption = null;
        try {
            updatedConsumption = consumptionService.update(id, consumptionDTO);
        } catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedConsumption, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        consumptionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}