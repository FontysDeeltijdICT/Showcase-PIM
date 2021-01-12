package com.mitchellton.pim;

import com.mitchellton.pim.dao.DatasheetDao;
import com.mitchellton.pim.dao.PartDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeDatasheetDataAccessService implements DatasheetDao {
    private static List<DatasheetDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, DatasheetDo datasheetDo) {
        DB.add(new DatasheetDo(id, datasheetDo.getPartId(), datasheetDo.getFilename()));
        return 1;
    }

    @Override
    public List<DatasheetDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<DatasheetDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<DatasheetDo> partMaybe = selectById(id);
        if(!partMaybe.isPresent())
            return 0;
        DB.remove(partMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, DatasheetDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new DatasheetDo(id, update.getPartId(), update.getFilename()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }

    @Override
    public List<DatasheetDo> selectByPartId(UUID id) {
        return null;
    }
}
