package com.mitchellton.pim.mappers;

import com.mitchellton.pim.DistributorDo;
import com.mitchellton.pim.PartDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("distributorMapper")
public class DistributorMapper implements BaseMapper<DistributorDo> {
    @Override
    public DistributorDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DistributorDo distributor = new DistributorDo(
                UUID.fromString(rs.getString("UUID")),
                rs.getString("name"),
                rs.getString("url"),
                rs.getString("address"),
                rs.getString("phonenumber"),
                rs.getString("contactperson")
        );
        return distributor;
    }

    public MapSqlParameterSource mapObject(UUID id, DistributorDo distributorDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("name", distributorDo.getName());
        namedParameters.addValue("url", distributorDo.getUrl());
        namedParameters.addValue("address", distributorDo.getAddress());
        namedParameters.addValue("phonenumber", distributorDo.getPhonenumber());
        namedParameters.addValue("contactperson", distributorDo.getContactperson());
        return namedParameters;
    }
}
