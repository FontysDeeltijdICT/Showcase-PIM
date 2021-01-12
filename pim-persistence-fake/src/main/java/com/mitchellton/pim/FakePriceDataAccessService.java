package com.mitchellton.pim;

import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.dao.PriceDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FakePriceDataAccessService implements PriceDao {
    private static List<PriceDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, PriceDo priceDo) {
        DB.add(new PriceDo(id, priceDo.getPartId(), priceDo.getDistributorId(), priceDo.getMinOderAmount(), priceDo.getPrice()));
        return 1;
    }

    @Override
    public List<PriceDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<PriceDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<PriceDo> priceMaybe = selectById(id);
        if(!priceMaybe.isPresent())
            return 0;
        DB.remove(priceMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, PriceDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new PriceDo(id, update.getPartId(), update.getDistributorId(), update.getMinOderAmount(), update.getPrice()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }

    @Override
    public List<PriceDo> selectByPartId(UUID id) {
        return DB.stream()
                .filter(part -> part.getPartId().equals(id))
                .collect(Collectors.toList());
    }
}
