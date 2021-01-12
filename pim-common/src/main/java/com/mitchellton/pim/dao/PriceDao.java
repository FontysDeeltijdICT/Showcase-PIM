package com.mitchellton.pim.dao;

import com.mitchellton.pim.PriceDo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceDao extends Dao<PriceDo> {

    List<PriceDo> selectByPartId(UUID id);

}