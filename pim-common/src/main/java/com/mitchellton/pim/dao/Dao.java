package com.mitchellton.pim.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {

    int insert(UUID id, T dao);

    default int insert(T dao) {
        UUID id = UUID.randomUUID();
        return insert(id, dao);
    }

    List<T> selectAll();

    Optional<T> selectById(UUID id);

    int deleteById(UUID id);

    int updateById(UUID id, T dao);

}
