package com.mitchellton.pim;

import com.mitchellton.pim.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/part")
@RestController
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public void addPart(@Valid @NotNull @RequestBody PartJsonObject partJsonObject) {
        partService.add(partJsonObject);
    }

    @GetMapping
    public List<PartJsonObject> getAllParts() {
        @SuppressWarnings("unchecked")
        List<PartJsonObject> response = (List) partService.getAll();
        return response;
    }

    @GetMapping(path = "{id}")
    public PartJsonObject getPartById(@PathVariable("id") UUID id) {
        PartDo response = partService.getById(id).orElse(null);
        if(response == null) return null;
        return new PartJsonObject(response);
    }

    @DeleteMapping(path = "{id}")
    public void deletePartById(@PathVariable("id") UUID id) {
        partService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody PartJsonObject partJsonObject) {
        partService.update(id, partJsonObject);
    }
}
