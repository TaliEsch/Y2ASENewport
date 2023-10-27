package com.example.wastemanagement.service;

import com.example.wastemanagement.config.SecurityConfig;
import com.example.wastemanagement.data.AccountRepositoryJpa;

import com.example.wastemanagement.domain.Users;
import com.example.wastemanagement.web.Form.AdminCreationForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepositoryJpa accountRepositoryJpa;

    PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepositoryJpa accountRepository) {

        this.accountRepositoryJpa = accountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // This function puts the account form data into a User variable and sends it to the repository to be saved.
    public void addAccount(AdminCreationForm accountForm) throws IOException {

        accountForm.setPassword(SecurityConfig.manualPasswordHasher().encode(accountForm.getPassword()));
        accountForm.setUser_Role("ROLE_"+accountForm.getUser_Role());
        Users account = new Users(accountForm.getUsername(),accountForm.getPassword(),true, Collections.singleton(accountForm.getUser_Role()));

        accountRepositoryJpa.save(account);

    }

    // This finds the account details based by the username provided and asking the repository to search for it.
    @Override
    public Users findAccountCredentialsByAccountName(String account_Name) {
        try{
            var account = accountRepositoryJpa.findUsersByAccountName(account_Name);
            return account;
        } catch (Exception e){
            System.out.println("Account not found!");
        }
        return null;
    }

}
