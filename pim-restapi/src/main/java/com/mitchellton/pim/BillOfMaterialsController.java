package com.mitchellton.pim;

import com.mitchellton.pim.service.BillOfMaterialsService;
import com.mitchellton.pim.service.DatasheetService;
import com.mitchellton.pim.service.PriceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/bom")
@RestController
public class BillOfMaterialsController {

    private final BillOfMaterialsService billOfMaterialsService;
    private final PriceCheckService priceCheckService;

    @Autowired
    public BillOfMaterialsController(BillOfMaterialsService billOfMaterialsService, PriceCheckService priceCheckService) {
        this.billOfMaterialsService = billOfMaterialsService;
        this.priceCheckService = priceCheckService;
    }

    @PostMapping
    public void addBom(@Valid @NotNull @RequestBody BillOfMaterialsJsonObject billOfMaterialsJsonObject) {
        billOfMaterialsService.add(billOfMaterialsJsonObject);
    }

    @GetMapping
    public List<BillOfMaterialsJsonObject> getAllBoms() {
        @SuppressWarnings("unchecked")
        List<BillOfMaterialsJsonObject> response = (List) billOfMaterialsService.getAll();
        return response;
    }

    @GetMapping(value = "/{id}")
    public BillOfMaterialsJsonObject getById(@PathVariable("id") UUID id) {
        BillOfMaterialsDo response = billOfMaterialsService.getById(id).orElse(null);
        if(response == null) return null;
        return new BillOfMaterialsJsonObject(response);
    }

    @GetMapping(value = "/{id}/pricecheck")
    public List<ShoppingListItem> getShoppingList(@PathVariable("id") UUID id) {
        List<ShoppingListItem> response = priceCheckService.getShoppingList(id);
        return response;
    }

    @GetMapping(value = "/{id}/pricecheck/bydistributor")
    public HashMap<UUID, List<ShoppingListItem>> getShoppingListByDistributor(@PathVariable("id") UUID id) {
        HashMap<UUID, List<ShoppingListItem>> response = priceCheckService.getShoppingListByDistributor(id);
        return response;
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        billOfMaterialsService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateBom(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody BillOfMaterialsJsonObject billOfMaterialsJsonObject) {
        billOfMaterialsService.update(id, billOfMaterialsJsonObject);
    }

}
