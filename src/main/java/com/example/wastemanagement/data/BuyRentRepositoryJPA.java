package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.buyRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
//note: I used Long for Id rather than Integer
public interface BuyRentRepositoryJPA extends JpaRepository<buyRent, Long>{

    List<buyRent> findAll();

    Optional<buyRent> findById(Long itemId);
}

