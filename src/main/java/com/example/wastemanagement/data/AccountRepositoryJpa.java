package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<Users, String> {

    Users findUsersByAccountName(String account_Name);

    Users findUser_RoleByAccountName(String account_Name);

}
