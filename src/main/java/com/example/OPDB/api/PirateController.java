package com.example.OPDB.api;

import com.example.OPDB.model.Pirate;
import com.example.OPDB.service.PirateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/pirate")
@RestController
public class PirateController {

    private final PirateService pirateService;

    @Autowired
    public PirateController(PirateService pirateService) {
        this.pirateService = pirateService;
    }

    @PostMapping
    public void addPirate(@RequestBody Pirate pirate) {
        pirateService.addPirate(pirate);
    }

    @GetMapping
    public List<Pirate> getAllPeople(){
        return pirateService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Pirate getPirateById(@PathVariable("id") UUID id) {
        return pirateService.getPirateById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePirateById(@PathVariable("id") UUID id){
        pirateService.deletePirate(id);
    }

    @PutMapping(path = "{id}")
    public void updatePirate(@PathVariable("id") UUID id, @RequestBody Pirate pirateToUpdate){
        pirateService.updatePirate(id, pirateToUpdate);
    }
}

