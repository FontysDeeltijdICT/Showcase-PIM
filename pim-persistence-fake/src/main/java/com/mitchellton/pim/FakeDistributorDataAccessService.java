package com.mitchellton.pim;

import com.mitchellton.pim.dao.DistributorDao;
import com.mitchellton.pim.dao.PartDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeDistributorDataAccessService implements DistributorDao {
    private static List<DistributorDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, DistributorDo distributorDo) {
        DB.add(new DistributorDo(id, distributorDo.getName(), distributorDo.getUrl(), distributorDo.getAddress(),distributorDo.getPhonenumber(), distributorDo.getContactperson()));
        return 1;
    }

    @Override
    public List<DistributorDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<DistributorDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<DistributorDo> partMaybe = selectById(id);
        if(!partMaybe.isPresent())
            return 0;
        DB.remove(partMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, DistributorDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new DistributorDo(id, update.getName(), update.getUrl(), update.getAddress(),update.getPhonenumber(), update.getContactperson()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }
}
