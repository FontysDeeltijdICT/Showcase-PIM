package com.mitchellton.pim.mappers;

import com.mitchellton.pim.DatasheetDo;
import com.mitchellton.pim.PartDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("datasheetMapper")
public class DatasheetMapper implements BaseMapper<DatasheetDo> {
    @Override
    public DatasheetDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DatasheetDo datasheetDo = new DatasheetDo(
                UUID.fromString(rs.getString("UUID")),
                UUID.fromString(rs.getString("partUUID")),
                rs.getString("filename")
        );
        return datasheetDo;
    }

    public MapSqlParameterSource mapObject(UUID id, DatasheetDo datasheetDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("partuuid", datasheetDo.getPartId().toString());
        namedParameters.addValue("filename", datasheetDo.getFilename());
        return namedParameters;
    }
}
