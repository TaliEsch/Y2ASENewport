package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepositoryJpa extends JpaRepository<Repair, Integer> {
}
