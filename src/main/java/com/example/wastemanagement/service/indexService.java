package com.example.wastemanagement.service;


import com.example.wastemanagement.domain.index;
import com.example.wastemanagement.service.Dto.indexDTO;
import com.example.wastemanagement.web.Form.indexForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;


public interface indexService {
    void addIndex(indexForm iF) throws IOException;
    BindingResult checkForProfanities(indexForm iF, BindingResult bindingResult);

    indexDTO getIndex();

}
