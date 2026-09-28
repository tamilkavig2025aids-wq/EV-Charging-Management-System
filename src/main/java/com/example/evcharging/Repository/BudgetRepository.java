package com.example.evcharging.Repository;

import com.example.evcharging.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}