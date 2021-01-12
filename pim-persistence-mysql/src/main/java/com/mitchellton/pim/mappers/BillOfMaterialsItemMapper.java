package com.mitchellton.pim.mappers;

import com.mitchellton.pim.BillOfMaterialsDo;
import com.mitchellton.pim.BillOfMaterialsItemDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("billOfMaterialsItemMapper")
public class BillOfMaterialsItemMapper implements BaseMapper<BillOfMaterialsItemDo> {
    @Override
    public BillOfMaterialsItemDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        BillOfMaterialsItemDo billOfMaterialsItemDo = new BillOfMaterialsItemDo(
                UUID.fromString(rs.getString("UUID")),
                UUID.fromString(rs.getString("bomUUID")),
                UUID.fromString(rs.getString("partUUID")),
                rs.getInt("amount")
        );
        return billOfMaterialsItemDo;
    }

    public MapSqlParameterSource mapObject(UUID id, BillOfMaterialsItemDo billOfMaterialsItemDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("bomuuid", billOfMaterialsItemDo.getBomId().toString());
        namedParameters.addValue("partuuid", billOfMaterialsItemDo.getPartId().toString());
        namedParameters.addValue("amount", billOfMaterialsItemDo.getAmount());
        return namedParameters;
    }
}
