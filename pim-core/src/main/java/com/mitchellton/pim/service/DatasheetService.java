package com.mitchellton.pim.service;

import com.mitchellton.pim.dao.DatasheetDao;
import com.mitchellton.pim.DatasheetDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DatasheetService extends BaseService<DatasheetDao, DatasheetDo> {

    @Autowired
    public DatasheetService(@Qualifier("mySqlDatasheetDao") DatasheetDao dao) {
        super(dao);
    }

    public Optional<DatasheetDo> getByPartId(UUID id) {
        return dao.selectByPartId(id);
    }

}
