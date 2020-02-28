package com.ihai.accounting.tax.service;

import com.ihai.accounting.tax.model.IncomeTaxEntity;

import java.util.List;

public interface IncomeTaxService {

    List<IncomeTaxEntity> findByType(Byte taxType);

    Boolean existsByType(Byte taxType);
}
