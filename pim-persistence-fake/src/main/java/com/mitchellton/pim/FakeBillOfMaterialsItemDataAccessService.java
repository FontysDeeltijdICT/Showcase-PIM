package com.mitchellton.pim;

import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.BillOfMaterialsItemDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FakeBillOfMaterialsItemDataAccessService implements BillOfMaterialsItemDao {
    private static List<BillOfMaterialsItemDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, BillOfMaterialsItemDo billOfMaterialsItemDo) {
        DB.add(new BillOfMaterialsItemDo(id, billOfMaterialsItemDo.getBomId(), billOfMaterialsItemDo.getPartId(), billOfMaterialsItemDo.getAmount()));
        return 1;
    }

    @Override
    public List<BillOfMaterialsItemDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<BillOfMaterialsItemDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<BillOfMaterialsItemDo> partMaybe = selectById(id);
        if(!partMaybe.isPresent())
            return 0;
        DB.remove(partMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, BillOfMaterialsItemDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new BillOfMaterialsItemDo(id, update.getBomId(), update.getPartId(), update.getAmount()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }

    @Override
    public List<BillOfMaterialsItemDo> selectByBomId(UUID id) {
        return DB.stream()
                .filter(part -> part.getBomId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public int deleteByBomId(UUID id) {
        return 0;
    }
}
