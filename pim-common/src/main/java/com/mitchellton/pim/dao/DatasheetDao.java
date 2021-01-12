package com.mitchellton.pim.dao;

import com.mitchellton.pim.DatasheetDo;

import java.util.List;
import java.util.UUID;

public interface DatasheetDao extends Dao<DatasheetDo> {

    List<DatasheetDo> selectByPartId(UUID id);

}
