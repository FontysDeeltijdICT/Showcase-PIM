package com.mitchellton.pim.service;

import com.mitchellton.pim.DatasheetDo;
import com.mitchellton.pim.PriceDo;
import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.PartDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartService extends BaseService<PartDao, PartDo> {

    private final PriceService priceService;
    private final DatasheetService datasheetService;

    @Autowired
    public PartService(@Qualifier("mySqlPartDao") PartDao dao, PriceService priceService, DatasheetService datasheetService) {
        super(dao);
        this.priceService = priceService;
        this.datasheetService = datasheetService;
    }

    public Optional<PartDo> getById(UUID id, boolean withSubfields) {
        Optional<PartDo> part = dao.selectById(id);
        if(part.isPresent() && withSubfields) {
            List<PriceDo> prices = priceService.getByPartId(id);
            part.get().setPrices(prices);
            List<DatasheetDo> datasheets = datasheetService.getByPartId(id);
            part.get().setDatasheets(datasheets);
        }
        return part;
    }

    @Override
    public Optional<PartDo> getById(UUID id) {
        return getById(id,true);
    }

}
