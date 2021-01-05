package com.mitchellton.pim.service;

import com.mitchellton.pim.DatasheetDo;
import com.mitchellton.pim.DistributorDo;
import com.mitchellton.pim.dao.DatasheetDao;
import com.mitchellton.pim.dao.DistributorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DistributorService extends BaseService<DistributorDao, DistributorDo> {

    @Autowired
    public DistributorService(@Qualifier("distributorMySqlDao") DistributorDao dao) {
        super(dao);
    }

}
