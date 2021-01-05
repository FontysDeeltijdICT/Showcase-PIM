package com.mitchellton.pim;

import com.mitchellton.pim.dao.DistributorDao;
import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.mappers.DistributorMapper;
import com.mitchellton.pim.mappers.PartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("distributorMySqlDao")
public class DistributorMySqlDataAccessService implements DistributorDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DistributorMapper distributorMapper;

    @Override
    public int insert(UUID id, DistributorDo distributorDo) {
        String sql = "INSERT INTO `distributors` (`UUID`, `name`, `url`, `address`, `phonenumber`, `contactperson`) VALUES (:uuid, :name, :url, :address, :phonenumber, :contactperson)";
        MapSqlParameterSource namedParameters = distributorMapper.mapObject(id, distributorDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<DistributorDo> selectAll() {
        String sql = "SELECT * FROM Distributors";
        List<DistributorDo> distributors = namedParameterJdbcTemplate.query(sql, distributorMapper);
        return distributors;
    }

    @Override
    public Optional<DistributorDo> selectById(UUID id) {
        String sql = "SELECT * FROM Distributors WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<DistributorDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, distributorMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `distributors` WHERE `distributors`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, DistributorDo distributorDo) {
        String sql = "UPDATE `distributors` SET `name` = :name, `url` = :url, `address` = :address, `phonenumber` = :phonenumber, `contactperson` = :contactperson WHERE `distributors`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = distributorMapper.mapObject(id, distributorDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
