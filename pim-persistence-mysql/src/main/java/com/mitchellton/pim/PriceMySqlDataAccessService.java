package com.mitchellton.pim;

import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.dao.PriceDao;
import com.mitchellton.pim.mappers.PartMapper;
import com.mitchellton.pim.mappers.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mySqlPriceDao")
public class PriceMySqlDataAccessService implements PriceDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public int insert(UUID id, PriceDo priceDo) {
        String sql = "INSERT INTO `prices` (`UUID`, `partUUID`, `distributorUUID`, `minOderAmount`, `price`) VALUES (:uuid, :partUuid, :distributorUuid, :minOderAmount, :price)";
        MapSqlParameterSource namedParameters = priceMapper.mapObject(id, priceDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<PriceDo> selectAll() {
        String sql = "SELECT * FROM prices";
        List<PriceDo> prices = namedParameterJdbcTemplate.query(sql, priceMapper);
        return prices;
    }

    @Override
    public Optional<PriceDo> selectById(UUID id) {
        String sql = "SELECT * FROM Prices WHERE UUID = :uuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uuid", id.toString());
        List<PriceDo> price = namedParameterJdbcTemplate.query(sql, namedParameters, priceMapper);
        if(price.size() == 0) return Optional.empty();
        return Optional.of(price.get(0));
    }

    @Override
    public List<PriceDo> selectByPartId(UUID id) {
        String sql = "SELECT * FROM Prices WHERE partUUID = :partuuid";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("partuuid", id.toString());
        List<PriceDo> prices = namedParameterJdbcTemplate.query(sql, namedParameters, priceMapper);
        return prices;
    }

    @Override
    public int deleteById(UUID id) {
        String sql = "DELETE FROM `prices` WHERE `prices`.`UUID` = :uuid";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("uuid", id.toString());
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int updateById(UUID id, PriceDo priceDo) {
        String sql = "UPDATE `prices` SET `partUUID` = :partUuid, `distributorUUID` = :distributorUuid, `minOderAmount` = :minOrderAmount, `price` = :price WHERE `prices`.`UUID` = :uuid;";
        MapSqlParameterSource namedParameters = priceMapper.mapObject(id, priceDo);
        return namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}