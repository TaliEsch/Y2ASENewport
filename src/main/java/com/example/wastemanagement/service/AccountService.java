package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Users;
import com.example.wastemanagement.web.Form.AdminCreationForm;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AccountService {

    void addAccount(AdminCreationForm accountForm) throws IOException;
    Users findAccountCredentialsByAccountName(String account_Name);

}
