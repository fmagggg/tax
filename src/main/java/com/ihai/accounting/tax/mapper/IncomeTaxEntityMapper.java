package com.ihai.accounting.tax.mapper;

import com.ihai.accounting.tax.model.IncomeTaxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IncomeTaxEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IncomeTaxEntity record);

    int insertSelective(IncomeTaxEntity record);

    IncomeTaxEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IncomeTaxEntity record);

    int updateByPrimaryKey(IncomeTaxEntity record);

    List<IncomeTaxEntity> selectByType(Byte taxType);

    long countByType(Byte taxType);
}