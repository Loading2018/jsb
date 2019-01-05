package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.RemittanceTransaction;

public interface GeneralRemittanceService {
    void addGeneralRemittance();
    RemittanceTransaction addGeneralRemittancerecord(RemittanceTransaction remittanceTransaction);
    boolean updateState(RemittanceTransaction remittanceTransaction);
}
