package com.mitchellton.pim.mappers;

import com.mitchellton.pim.BillOfMaterialsDo;
import com.mitchellton.pim.DatasheetDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("billOfMaterialsMapper")
public class BillOfMaterialsMapper implements BaseMapper<BillOfMaterialsDo> {
    @Override
    public BillOfMaterialsDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        BillOfMaterialsDo billOfMaterialsDo = new BillOfMaterialsDo(
                UUID.fromString(rs.getString("UUID")),
                rs.getString("bomName"),
                rs.getString("projectName"),
                rs.getString("notes")
        );
        return billOfMaterialsDo;
    }

    public MapSqlParameterSource mapObject(UUID id, BillOfMaterialsDo billOfMaterialsDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("bomName", billOfMaterialsDo.getBomName());
        namedParameters.addValue("projectName", billOfMaterialsDo.getProjectName());
        namedParameters.addValue("notes", billOfMaterialsDo.getNotes());
        return namedParameters;
    }
}
