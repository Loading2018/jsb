package com.cloud.jsproducerremittance.dao;

import com.cloud.jsproducerremittance.entity.RemittanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface remittancetransactionDao extends JpaRepository<RemittanceTransaction,Long> {
    public List<RemittanceTransaction> findByRemittancetransactioncurrent(String cardNumber);
    public List<RemittanceTransaction> findByRemittancetransactionnumber(String cardNumber);
    public List<RemittanceTransaction> findByRemittancetransactioncardnumber(String cardNumber);

}
