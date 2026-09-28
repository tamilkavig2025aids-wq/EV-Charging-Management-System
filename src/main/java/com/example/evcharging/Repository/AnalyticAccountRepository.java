package com.example.evcharging.Repository;

import com.example.evcharging.Entity.AnalyticAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticAccountRepository extends JpaRepository<AnalyticAccount, Long> {
}