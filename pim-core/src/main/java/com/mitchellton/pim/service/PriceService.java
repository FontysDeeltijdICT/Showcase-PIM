package com.mitchellton.pim.service;

import com.mitchellton.pim.DatasheetDo;
import com.mitchellton.pim.DistributorDo;
import com.mitchellton.pim.PriceDo;
import com.mitchellton.pim.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceService extends BaseService<PriceDao, PriceDo> {

    private final DistributorService distributorService;

    @Autowired
    public PriceService(@Qualifier("mySqlPriceDao") PriceDao dao, DistributorService distributorService) {
        super(dao);
        this.distributorService = distributorService;
    }

    @Override
    public Optional<PriceDo> getById(UUID id) {
        Optional<PriceDo> price = dao.selectById(id);
        if(price.isPresent()) {
            Optional<DistributorDo> distributor = distributorService.getById(price.get().getDistributorId());
            if(distributor.isPresent()) price.get().setDistributor(distributor.get());
        }
        return price;
    }

    public List<PriceDo> getByPartId(UUID id) {
        return dao.selectByPartId(id);
    }

    public PriceDo getCheapestPrice(UUID partId, int amount) {
        // Get all prices for part
        List<PriceDo> prices = this.getCheapestPrices(partId, amount);

        if(prices.size() > 0)
            return prices.get(0);
        return null;
    }

    public List<PriceDo> getCheapestPrices(UUID partId, int amount) {
        // Get all prices for part
        List<PriceDo> prices = dao.selectByPartId(partId);
        List<PriceDo> result = new ArrayList<>();

        // Sort prices from cheapest to most expensive
        prices.sort(Comparator.comparingDouble(PriceDo::getPrice));

        for (PriceDo price : prices) {
            if(price.getMinOderAmount() <= amount) {
                result.add(price);
            }
        }

        return result;
    }
}
