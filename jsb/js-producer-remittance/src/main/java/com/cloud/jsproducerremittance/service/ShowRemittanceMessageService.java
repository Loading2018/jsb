package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.RemittanceTransaction;

import java.util.List;

public interface ShowRemittanceMessageService {
    List<RemittanceTransaction> showRemittance(String cardNumber);
    List<RemittanceTransaction> showGathering(String cardNumber);
}
