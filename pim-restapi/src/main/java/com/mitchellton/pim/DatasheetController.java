package com.mitchellton.pim;

import com.mitchellton.pim.service.DatasheetService;
import com.mitchellton.pim.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/datasheet")
@RestController
public class DatasheetController {
    private final DatasheetService datasheetService;

    @Autowired
    public DatasheetController(DatasheetService datasheetService) {
        this.datasheetService = datasheetService;
    }

    @PostMapping
    public void addDatasheet(@Valid @NotNull @RequestBody DatasheetJsonObject datasheetJsonObject) {
        datasheetService.add(datasheetJsonObject);
    }

    @GetMapping
    public List<DatasheetJsonObject> getAllDatasheets() {
        @SuppressWarnings("unchecked")
        List<DatasheetJsonObject> response = (List) datasheetService.getAll();
        return response;
    }

    @GetMapping(value = "/{id}")
    public DatasheetJsonObject getById(@PathVariable("id") UUID id) {
        DatasheetDo response = datasheetService.getById(id).orElse(null);
        if(response == null) return null;
        return new DatasheetJsonObject(response);
    }

    @GetMapping(value = "/part/{partid}")
    public DatasheetJsonObject getByPartId(@PathVariable("partid") UUID id) {
        DatasheetDo response = datasheetService.getByPartId(id).orElse(null);
        if(response == null) return null;
        return new DatasheetJsonObject(response);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        datasheetService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updatePart(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody DatasheetJsonObject datasheetJsonObject) {
        datasheetService.update(id, datasheetJsonObject);
    }
}
