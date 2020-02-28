package com.ihai.accounting.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeTaxEntity {
    private Long id;

    private String code;

    private Byte taxType;

    private Byte stage;

    private Integer taxableIncomeStart;

    private Integer taxableIncomeEnd;

    private Byte taxRate;

    private Integer quickCalculationDeduction;

    private Byte isDelete;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getTaxType() {
        return taxType;
    }

    public void setTaxType(Byte taxType) {
        this.taxType = taxType;
    }

    public Byte getStage() {
        return stage;
    }

    public void setStage(Byte stage) {
        this.stage = stage;
    }

    public Integer getTaxableIncomeStart() {
        return taxableIncomeStart;
    }

    public void setTaxableIncomeStart(Integer taxableIncomeStart) {
        this.taxableIncomeStart = taxableIncomeStart;
    }

    public Integer getTaxableIncomeEnd() {
        return taxableIncomeEnd;
    }

    public void setTaxableIncomeEnd(Integer taxableIncomeEnd) {
        this.taxableIncomeEnd = taxableIncomeEnd;
    }

    public Byte getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Byte taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getQuickCalculationDeduction() {
        return quickCalculationDeduction;
    }

    public void setQuickCalculationDeduction(Integer quickCalculationDeduction) {
        this.quickCalculationDeduction = quickCalculationDeduction;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}