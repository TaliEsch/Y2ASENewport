package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Rs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RsService {
    List<Rs> getUsesByCategoryId(Integer categoryId);
}
