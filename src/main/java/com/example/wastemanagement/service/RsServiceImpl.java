package com.example.wastemanagement.service;

import com.example.wastemanagement.data.RsRepositoryJpa;
import com.example.wastemanagement.domain.Rs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RsServiceImpl implements RsService {
    private final RsRepositoryJpa rsRepositoryJpa;

    public RsServiceImpl(RsRepositoryJpa rsRepositoryJpa) {
        this.rsRepositoryJpa = rsRepositoryJpa;
    }

    // uses the useRepositoryJpa to find the applications by category id
    @Override
    public List<Rs> getUsesByCategoryId(Integer categoryId) {
        return rsRepositoryJpa.findUseByCategoryId(categoryId);
    }
}
