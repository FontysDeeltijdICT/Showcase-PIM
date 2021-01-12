package com.mitchellton.pim;

import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.DatasheetDao;
import com.mitchellton.pim.mappers.BillOfMaterialsMapper;
import com.mitchellton.pim.mappers.DatasheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mySqlBillOfMaterialsDao")
public class BillOfMaterialsMySqlDataAccessService implements BillOfMaterialsDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private BillOfMaterialsMapper billOfMaterialsMapper;

    @Override
    public int insert(UUID id, BillOfMaterialsDo billOfMaterialsDo) {
        String sql = "INSERT INTO `boms` (`UUID`, `bomName`, `projectName`, `notes`) VALUES (:uuid, :bomName, :projectName, :notes)";
        MapSqlParameterSource namedParameters = billOfMaterialsMapper.mapObject(id, billOfMaterialsDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<BillOfMaterialsDo> selectAll() {
        String sql = "SELECT * FROM boms";
        List<BillOfMaterialsDo> parts = namedParameterJdbcTemplate.query(sql,billOfMaterialsMapper);
        return parts;
    }

    @Override
    public Optional<BillOfMaterialsDo> selectById(UUID id) {
        String sql = "SELECT * FROM boms WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<BillOfMaterialsDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, billOfMaterialsMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `boms` WHERE `boms`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, BillOfMaterialsDo billOfMaterialsDo) {
        String sql = "UPDATE `boms` SET `bomName` = :bomName, `projectName` = :projectName , `notes` = :notes WHERE `boms`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = billOfMaterialsMapper.mapObject(id, billOfMaterialsDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
