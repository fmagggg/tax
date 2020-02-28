package com.ihai.accounting.tax.service.impl;

import com.ihai.accounting.tax.mapper.IncomeTaxEntityMapper;
import com.ihai.accounting.tax.model.IncomeTaxEntity;
import com.ihai.accounting.tax.service.IncomeTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeTaxServiceImpl implements IncomeTaxService {

    @Autowired
    private IncomeTaxEntityMapper incomeTaxEntityMapper;


    @Override
    public List<IncomeTaxEntity> findByType(Byte taxType) {
        return incomeTaxEntityMapper.selectByType(taxType);
    }

    @Override
    public Boolean existsByType(Byte taxType) {
        long count = incomeTaxEntityMapper.countByType(taxType);
        return count > 0;
    }
}
