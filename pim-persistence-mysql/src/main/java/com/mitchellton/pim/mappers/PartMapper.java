package com.mitchellton.pim.mappers;

import com.mitchellton.pim.PartDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("partMapper")
public class PartMapper implements BaseMapper<PartDo> {
    @Override
    public PartDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartDo part = new PartDo(
                UUID.fromString(rs.getString("UUID")),
                rs.getString("name"),
                rs.getInt("value"),
                rs.getInt("unit"),
                rs.getString("description")
        );
        return part;
    }

    public MapSqlParameterSource mapObject(UUID id, PartDo partDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("name", partDo.getName());
        namedParameters.addValue("value", partDo.getValue());
        namedParameters.addValue("unit", partDo.getUnit());
        namedParameters.addValue("description", partDo.getDescription());
        return namedParameters;
    }
}
