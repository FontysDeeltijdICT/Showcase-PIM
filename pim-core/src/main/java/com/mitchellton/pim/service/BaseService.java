package com.mitchellton.pim.service;

import com.mitchellton.pim.dao.Dao;
import com.mitchellton.pim.Do;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<DAO extends Dao<DO>, DO extends Do> {
    protected final DAO dao;

    public BaseService(DAO dao) {
        this.dao = dao;
    }

    public int add(DO dataObject) {
        return dao.insert(dataObject);
    }

    public List<DO> getAll() {
        return dao.selectAll();
    }

    public Optional<DO> getById(UUID id) {
        return dao.selectById(id);
    }

    public int delete(UUID id) {
        return dao.deleteById(id);
    }

    public int update(UUID id, DO dataObject) {
        return dao.updateById(id, dataObject);
    }
}
