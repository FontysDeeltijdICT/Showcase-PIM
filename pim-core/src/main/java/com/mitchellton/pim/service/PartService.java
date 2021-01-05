package com.mitchellton.pim.service;

import com.mitchellton.pim.dao.PartDao;
import com.mitchellton.pim.PartDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PartService extends BaseService<PartDao, PartDo> {

    @Autowired
    public PartService(@Qualifier("mySqlPartDao") PartDao dao) {
        super(dao);
    }

}
