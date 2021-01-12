package com.mitchellton.pim;

import com.mitchellton.pim.dao.BillOfMaterialsDao;
import com.mitchellton.pim.dao.BillOfMaterialsItemDao;
import com.mitchellton.pim.mappers.BillOfMaterialsItemMapper;
import com.mitchellton.pim.mappers.BillOfMaterialsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mySqlBillOfMaterialsItemDao")
public class BillOfMaterialsItemMySqlDataAccessService implements BillOfMaterialsItemDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private BillOfMaterialsItemMapper billOfMaterialsItemMapper;

    @Override
    public int insert(UUID id, BillOfMaterialsItemDo billOfMaterialsItemDo) {
        String sql = "INSERT INTO `bomitems` (`UUID`, `bomUUID`, `partUUID`, `amount`) VALUES (:uuid, :bomuuid, :partuuid, :amount)";
        MapSqlParameterSource namedParameters = billOfMaterialsItemMapper.mapObject(id, billOfMaterialsItemDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<BillOfMaterialsItemDo> selectAll() {
        String sql = "SELECT * FROM bomitems";
        List<BillOfMaterialsItemDo> bomitems = namedParameterJdbcTemplate.query(sql, billOfMaterialsItemMapper);
        return bomitems;
    }

    @Override
    public Optional<BillOfMaterialsItemDo> selectById(UUID id) {
        String sql = "SELECT * FROM bomitems WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<BillOfMaterialsItemDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, billOfMaterialsItemMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public List<BillOfMaterialsItemDo> selectByBomId(UUID id) {
        String sql = "SELECT * FROM bomitems WHERE bomUUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<BillOfMaterialsItemDo> items = namedParameterJdbcTemplate.query(sql, namedParameters, billOfMaterialsItemMapper);
        return items;
    }

    @Override
    public int deleteByBomId(UUID id) {
        String sql = "DELETE FROM `bomitems` WHERE `bomitems`.`bomUUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `bomitems` WHERE `bomitems`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, BillOfMaterialsItemDo billOfMaterialsItemDo) {
        String sql = "UPDATE `bomitems` SET `bomUUID` = :bomuuid, `partUUID` = :partuuid , `amount` = :amount WHERE `bomitems`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = billOfMaterialsItemMapper.mapObject(id, billOfMaterialsItemDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
