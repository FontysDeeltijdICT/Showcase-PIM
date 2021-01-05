package com.mitchellton.pim;

import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.mappers.PartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mySqlPartDao")
public class PartMySqlDataAccessService implements PartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PartMapper partMapper;

    @Override
    public int insert(UUID id, PartDo partDo) {
        String sql = "INSERT INTO `parts` (`UUID`, `name`, `value`, `unit`, `description`) VALUES (:uuid, :name, :value, :unit, :description)";
        MapSqlParameterSource namedParameters = partMapper.mapObject(id, partDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<PartDo> selectAll() {
        String sql = "SELECT * FROM Parts";
        List<PartDo> parts = namedParameterJdbcTemplate.query(sql,partMapper);
        return parts;
    }

    @Override
    public Optional<PartDo> selectById(UUID id) {
        String sql = "SELECT * FROM Parts WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<PartDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, partMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `parts` WHERE `parts`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, PartDo partDo) {
        String sql = "UPDATE `parts` SET `name` = :name, `value` = :value, `unit` = :unit, `description` = :description WHERE `parts`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = partMapper.mapObject(id, partDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
