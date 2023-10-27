package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface indexRepositoryJPA extends JpaRepository<index, Long> {

}
