package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Rs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsRepositoryJpa extends JpaRepository<Rs,String> {

    @Query("SELECT u FROM Rs u WHERE u.categoryId = ?1")
    List<Rs> findUseByCategoryId(Integer categoryId);
}
