package com.mitchellton.pim;

import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.DatasheetDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeBillOfMaterialsDataAccessService implements BillOfMaterialsDao {
    private static List<BillOfMaterialsDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, BillOfMaterialsDo billOfMaterialsDo) {
        DB.add(new BillOfMaterialsDo(id, billOfMaterialsDo.getBomName(), billOfMaterialsDo.getProjectName(), billOfMaterialsDo.getNotes()));
        return 1;
    }

    @Override
    public List<BillOfMaterialsDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<BillOfMaterialsDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<BillOfMaterialsDo> partMaybe = selectById(id);
        if(!partMaybe.isPresent())
            return 0;
        DB.remove(partMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, BillOfMaterialsDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new BillOfMaterialsDo(id, update.getBomName(), update.getProjectName(), update.getNotes()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }
}
