package com.mitchellton.pim.mappers;

import com.mitchellton.pim.PartDo;
import com.mitchellton.pim.PriceDo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("priceMapper")
public class PriceMapper implements BaseMapper<PriceDo> {
    @Override
    public PriceDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PriceDo price = new PriceDo(
                UUID.fromString(rs.getString("UUID")),
                UUID.fromString(rs.getString("partUUID")),
                UUID.fromString(rs.getString("distributorUUID")),
                rs.getInt("minOderAmount"),
                rs.getDouble("price")
        );
        return price;
    }

    public MapSqlParameterSource mapObject(UUID id, PriceDo partDo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        namedParameters.addValue("partUuid", partDo.getPartId());
        namedParameters.addValue("distributorUuid", partDo.getDistributorId());
        namedParameters.addValue("minOderAmount", partDo.getMinOderAmount());
        namedParameters.addValue("price", partDo.getPrice());
        return namedParameters;
    }
}
