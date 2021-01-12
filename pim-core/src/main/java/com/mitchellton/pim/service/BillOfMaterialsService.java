package com.mitchellton.pim.service;

import com.mitchellton.pim.BillOfMaterialsDo;
import com.mitchellton.pim.BillOfMaterialsItemDo;
import com.mitchellton.pim.DatasheetDo;
import com.mitchellton.pim.PriceDo;
import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.BillOfMaterialsItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillOfMaterialsService extends BaseService<BillOfMaterialsDao, BillOfMaterialsDo> {

    BillOfMaterialsItemDao itemsDao;

    @Autowired
    public BillOfMaterialsService(@Qualifier("mySqlBillOfMaterialsDao") BillOfMaterialsDao dao, @Qualifier("mySqlBillOfMaterialsItemDao") BillOfMaterialsItemDao itemsDao) {
        super(dao);
        this.itemsDao = itemsDao;
    }

    public Optional<BillOfMaterialsDo> getById(UUID id) {
        Optional<BillOfMaterialsDo> bom = dao.selectById(id);
        if(bom.isPresent()) {
            List<BillOfMaterialsItemDo> items = itemsDao.selectByBomId(bom.get().getId());
            bom.get().setBomItems(items);
        }
        return bom;
    }

    public int delete(UUID id) {
        int ret = dao.deleteById(id);
        itemsDao.deleteByBomId(id);
        return ret;
    }
}
