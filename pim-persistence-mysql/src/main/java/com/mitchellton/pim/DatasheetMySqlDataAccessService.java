package com.mitchellton.pim;

import com.mitchellton.pim.dao.DatasheetDao;
import com.mitchellton.pim.mappers.DatasheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mySqlDatasheetDao")
public class DatasheetMySqlDataAccessService implements DatasheetDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DatasheetMapper datasheetMapper;

    @Override
    public int insert(UUID id, DatasheetDo datasheetDo) {
        String sql = "INSERT INTO `datasheets` (`UUID`, `partUUID`, `filename`) VALUES (:uuid, :partuuid, :filename)";
        MapSqlParameterSource namedParameters = datasheetMapper.mapObject(id, datasheetDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<DatasheetDo> selectAll() {
        String sql = "SELECT * FROM Datasheets";
        List<DatasheetDo> parts = namedParameterJdbcTemplate.query(sql,datasheetMapper);
        return parts;
    }

    @Override
    public Optional<DatasheetDo> selectById(UUID id) {
        String sql = "SELECT * FROM Datasheets WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<DatasheetDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, datasheetMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public Optional<DatasheetDo> selectByPartId(UUID id) {
        String sql = "SELECT * FROM Datasheets WHERE partUUID = :partuuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("partuuid", id.toString());
        List<DatasheetDo> parts = namedParameterJdbcTemplate.query(sql, namedParameters, datasheetMapper);
        if(parts.size() == 0) return Optional.empty();
        return Optional.of(parts.get(0));
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `datasheets` WHERE `datasheets`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, DatasheetDo datasheetDo) {
        String sql = "UPDATE `datasheets` SET `partUUID` = :partuuid, `filename` = :filename WHERE `datasheets`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = datasheetMapper.mapObject(id, datasheetDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
