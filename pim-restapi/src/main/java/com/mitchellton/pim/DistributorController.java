package com.mitchellton.pim;

import com.mitchellton.pim.service.DatasheetService;
import com.mitchellton.pim.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/distributor")
@RestController
public class DistributorController {
    private final DistributorService distributorService;

    @Autowired
    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @PostMapping
    public void addDistributor(@Valid @NotNull @RequestBody DistributorJsonObject distributorJsonObject) {
        distributorService.add(distributorJsonObject);
    }

    @GetMapping
    public List<DistributorJsonObject> getAllDistributors() {
        @SuppressWarnings("unchecked")
        List<DistributorJsonObject> response = (List) distributorService.getAll();
        return response;
    }

    @GetMapping(value = "/{id}")
    public DistributorJsonObject getById(@PathVariable("id") UUID id) {
        DistributorDo response = distributorService.getById(id).orElse(null);
        if(response == null) return null;
        return new DistributorJsonObject(response);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        distributorService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updatePart(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody DistributorJsonObject distributorJsonObject) {
        distributorService.update(id, distributorJsonObject);
    }
}
