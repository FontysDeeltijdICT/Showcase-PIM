package com.mitchellton.pim.service;

import com.mitchellton.pim.BillOfMaterialsDo;
import com.mitchellton.pim.BillOfMaterialsItemDo;
import com.mitchellton.pim.PriceDo;
import com.mitchellton.pim.ShoppingListItem;
import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.BillOfMaterialsItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceCheckService {

    BillOfMaterialsService billOfMaterialsService;
    PriceService priceService;
    PartService partService;

    @Autowired
    public PriceCheckService(BillOfMaterialsService billOfMaterialsService, PriceService priceService, PartService partService) {
        this.billOfMaterialsService = billOfMaterialsService;
        this.priceService = priceService;
        this.partService = partService;
    }

    public List<ShoppingListItem> getShoppingList(UUID bomUUID) {
        List<ShoppingListItem> shoppingListItems = new ArrayList<>();
        Optional<BillOfMaterialsDo> bom = billOfMaterialsService.getById(bomUUID);

        for (BillOfMaterialsItemDo item : bom.get().getBomItems()) {
            List<PriceDo> priceList = priceService.getCheapestPrices(item.getPartId(), item.getAmount());
            ShoppingListItem shoppingListItem = new ShoppingListItem(partService.getById(item.getPartId(), false).get(), item.getAmount(), priceList);
            shoppingListItems.add(shoppingListItem);
        }

        return shoppingListItems;
    }

    public HashMap<UUID, List<ShoppingListItem>> getShoppingListByDistributor(UUID bomUUID) {
        HashMap<UUID, List<ShoppingListItem>> distributors = new HashMap<>();
        List<ShoppingListItem> shoppingListItems = getShoppingList(bomUUID);

        for (ShoppingListItem item : shoppingListItems) {
            if (item.getPrices().size() > 0) {
                UUID distributorId = item.getPrices().get(0).getDistributorId();
                if (!distributors.containsKey(distributorId)) {
                    List<ShoppingListItem> list = new ArrayList<>();
                    list.add(item);

                    distributors.put(distributorId, list);
                } else {
                    distributors.get(distributorId).add(item);
                }
            }
        }

        return distributors;
    }
}
