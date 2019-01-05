package com.cloud.jsproducerremittance.dao;

import com.cloud.jsproducerremittance.entity.MakeRemittance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface makeremittanceDao extends JpaRepository<MakeRemittance,Long> {
}
