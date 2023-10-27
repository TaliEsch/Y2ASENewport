package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Repair;
import org.springframework.stereotype.Service;

@Service
public interface RepairService {
    void saveBooking(Repair booking);

    String[] sendEmail(Repair repair);
}
