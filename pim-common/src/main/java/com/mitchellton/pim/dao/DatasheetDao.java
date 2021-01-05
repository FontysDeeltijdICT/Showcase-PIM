package com.mitchellton.pim.dao;

import com.mitchellton.pim.DatasheetDo;

import java.util.Optional;
import java.util.UUID;

public interface DatasheetDao extends Dao<DatasheetDo> {

    Optional<DatasheetDo> selectByPartId(UUID id);

}
