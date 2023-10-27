package com.example.wastemanagement.service;


import com.example.wastemanagement.data.indexRepositoryJPA;

import com.example.wastemanagement.domain.index;
import com.example.wastemanagement.service.Dto.indexDTO;
import com.example.wastemanagement.web.Form.indexForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class indexServiceImpl implements indexService {

    private indexRepositoryJPA repositoryJPA;

    @Autowired
    public indexServiceImpl(indexRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    public BindingResult checkForProfanities(indexForm iF, BindingResult bindingResult) {
        // Checks for profanities in the description
        GlobalValidation global = new GlobalValidation();
        List<FieldError> banned = global.Validation(iF.getDescription(), "description", "indexForm");

        for (int i = 0; i < banned.size(); i++) {
            bindingResult.addError(banned.get(i));
        }
        if (bindingResult.hasErrors()) {
            System.out.println("LOG: User Error");
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        return bindingResult;
    }

    public void addIndex(indexForm iF) throws IOException {
        // Converts MultipartFile to byte Array
        byte[] imageByte1 = (iF.getImage1()).getBytes();
        byte[] imageByte2 = (iF.getImage2()).getBytes();
        byte[] imageByte3 = (iF.getImage3()).getBytes();
        byte[] imageByte4 = (iF.getImage4()).getBytes();

        index i = new index();
        i.setItemId(1L);
        Long count = repositoryJPA.count(Example.of(i));
        if (count == 1L) {
            repositoryJPA.deleteById(1L);
        }

        // Maps index to a new object that stores a byte array instead of a MultipartFile
        index indexToAdd = new index(1L, iF.getDescription(), imageByte1, imageByte2,
                imageByte3, imageByte4);

        //saves submission to database
        repositoryJPA.save(indexToAdd);
    }

    public String convertImageForDisplay(byte[] image) {
        if (image != null) {
            String imageString = Base64.getEncoder().encodeToString(image);
            return imageString;
        } else {
            return null;
        }
    }

    public indexDTO getIndex() {
        var allIndex = repositoryJPA.findAll();
        System.out.println("yo " + repositoryJPA.findAll()+""+ allIndex.size());
        index ind;
        // if index is null, create a new index with null values
        if (allIndex.size() == 0) {
            ind = new index(1L, null, null, null, null, null);
        }else{
            ind = allIndex.get(0);
        }
        indexDTO indexdto = new indexDTO(ind.getItemId(), ind.getDescription(),
                convertImageForDisplay(ind.getImage1()),
                convertImageForDisplay(ind.getImage2()),
                convertImageForDisplay(ind.getImage3()),
                convertImageForDisplay(ind.getImage4()));
        return indexdto;
    }
}
