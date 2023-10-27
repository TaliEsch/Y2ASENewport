package com.example.wastemanagement.service;


import com.example.wastemanagement.service.Dto.buyRentDto;
import com.example.wastemanagement.web.Form.BuyRentEmailForm;
import com.example.wastemanagement.web.Form.BuyRentForm;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface BuyRentService {
    BindingResult checkForProfanities(BuyRentForm buyRentForm, BindingResult bindingResult);

    void addBuyRent(BuyRentForm buyRentForm) throws IOException;

    List<buyRentDto> getAllBuyRent();

    List<buyRentDto> getSortedBuyRent(String searchQuery,
                                      String categoryFilter,
                                      String listingFilter,
                                      String priceFilter);

    List<buyRentDto> findItemById(Long itemId);

    void sendEmailResponse(BuyRentEmailForm buyRentEmailForm);

    void deleteItem(Long itemId);
}

