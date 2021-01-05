package com.mitchellton.pim;

import com.mitchellton.pim.dao.PartDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePartDataAccessService implements PartDao {
    private static List<PartDo> DB = new ArrayList<>();

    @Override
    public int insert(UUID id, PartDo partDo) {
        DB.add(new PartDo(id, partDo.getName(), partDo.getValue(), partDo.getUnit(), partDo.getDescription()));
        return 1;
    }

    @Override
    public List<PartDo> selectAll() {
        return DB;
    }

    @Override
    public Optional<PartDo> selectById(UUID id) {
        return DB.stream()
                .filter(part -> part.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<PartDo> partMaybe = selectById(id);
        if(partMaybe.isEmpty())
            return 0;
        DB.remove(partMaybe.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, PartDo update) {
        return selectById(id)
                .map(part -> {
                    int indexOfPartToUpdate = DB.indexOf(part);
                    if(indexOfPartToUpdate >= 0) {
                        DB.set(indexOfPartToUpdate, new PartDo(id, update.getName(), update.getValue(), update.getUnit(), update.getDescription()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }
}
