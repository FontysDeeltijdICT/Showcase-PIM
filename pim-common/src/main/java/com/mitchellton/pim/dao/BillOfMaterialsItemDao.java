package com.mitchellton.pim.dao;

import com.mitchellton.pim.BillOfMaterialsItemDo;
import com.mitchellton.pim.DatasheetDo;

import java.util.List;
import java.util.UUID;

public interface BillOfMaterialsItemDao extends Dao<BillOfMaterialsItemDo> {

    List<BillOfMaterialsItemDo> selectByBomId(UUID id);

    int deleteByBomId(UUID id);

}