package com.mitchellton.pim;

import com.mitchellton.pim.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/price")
@RestController
public class PriceController {
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public void addPart(@Valid @NotNull @RequestBody PriceJsonObject priceJsonObject) {
        priceService.add(priceJsonObject);
    }

    @GetMapping
    public List<PriceJsonObject> getAllPrices() {
        @SuppressWarnings("unchecked")
        List<PriceJsonObject> response = (List) priceService.getAll();
        return response;
    }

    @GetMapping(path = "{id}")
    public PriceJsonObject getPriceById(@PathVariable("id") UUID id) {
        PriceDo response = priceService.getById(id).orElse(null);
        if(response == null) return null;
        return new PriceJsonObject(response);
    }

    @GetMapping(value = "/part/{partid}")
    public List<PriceJsonObject> getPricesByPartId(@PathVariable("partid") UUID partId) {
        List<PriceJsonObject> response = (List) priceService.getByPartId(partId);
        return response;
    }

    @GetMapping(value = "/part/{partid}/best/{amount}")
    public PriceJsonObject getBestPrice(@PathVariable("partid") UUID partId, @PathVariable("amount") int amount) {
        PriceDo price = priceService.getCheapestPrice(partId, amount);
        return new PriceJsonObject(price);
    }

    @DeleteMapping(path = "{id}")
    public void deletePriceById(@PathVariable("id") UUID id) {
        priceService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody PriceJsonObject priceJsonObject) {
        priceService.update(id, priceJsonObject);
    }
}
