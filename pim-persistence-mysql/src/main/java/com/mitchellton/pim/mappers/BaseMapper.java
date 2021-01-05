package com.mitchellton.pim.mappers;

import com.mitchellton.pim.Do;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface BaseMapper<DO extends Do> extends RowMapper<DO> {
    DO mapRow(ResultSet rs, int rowNum)  throws SQLException;
    MapSqlParameterSource mapObject(UUID id, DO dataObject);
}
