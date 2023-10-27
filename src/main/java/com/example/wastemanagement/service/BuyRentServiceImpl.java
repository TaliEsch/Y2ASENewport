package com.example.wastemanagement.service;

import com.example.wastemanagement.data.BuyRentRepositoryJPA;
import com.example.wastemanagement.domain.buyRent;
import com.example.wastemanagement.service.Dto.buyRentDto;
import com.example.wastemanagement.web.Form.BuyRentEmailForm;
import com.example.wastemanagement.web.Form.BuyRentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BuyRentServiceImpl implements BuyRentService {

    private final BuyRentRepositoryJPA buyrentrepo;
    private final JavaMailSender javaMailSender;

    @Autowired
    public BuyRentServiceImpl(BuyRentRepositoryJPA buyrentrepo, JavaMailSender javaMailSender) {
        this.buyrentrepo = buyrentrepo;
        this.javaMailSender = javaMailSender;
    }

    public void addBuyRent(BuyRentForm buyRentForm) throws IOException {
        System.out.println("RESGDJF" + buyRentForm);
        // Converts MultipartFile to byte Array
        byte[] imageByte = (buyRentForm.getImage()).getBytes();

        // default rent
        String sale = "1";
        // Checking type of purchase
        if (buyRentForm.getItemSaleType().equals("Rent(week)")){
            sale = "0";
        }

        // Maps buyRent to a new object that stores a byte array instead of a MultipartFile
        buyRent buyRentToSave = new buyRent(null, buyRentForm.getItemName(), buyRentForm.getUsername(),
                buyRentForm.getEmail(), buyRentForm.getDescription(), buyRentForm.getPrice(), buyRentForm.getCategory(),
                sale, imageByte);

        //saves submission to database
        buyrentrepo.save(buyRentToSave);
    }

    public List<buyRentDto> convertToDto(List<buyRent> listBuyRent) {
/*         This method converts any inputted buyRent object to a buyRentDto object, preventing code from being
         duplicated */
        List<buyRentDto> buyRentDtoList = new ArrayList<>();
        String imageString;

        // For loop to loop through every item in the list
        for (buyRent buyrent : listBuyRent) {
            imageString = null;
            if (buyrent.getImage() != null && buyrent.getImage().length > 0) {
                // This will fail if the entered image is empty so the if/else statement is required
                imageString = Base64.getEncoder().encodeToString(buyrent.getImage());
            }
            // Maps to Dto
            buyRentDto buyRentDto = new buyRentDto(buyrent.getItemId(), buyrent.getItemName(), buyrent.getUsername(),
                    buyrent.getDescription(), buyrent.getPrice(), buyrent.getCategory(),
                    buyrent.getItemSaleType(), imageString);
            buyRentDtoList.add(buyRentDto);
        }
        return (buyRentDtoList);
    }

    public List<buyRentDto> getAllBuyRent() {
        // Returns all buyRent objects as a DTO
        var listBuyRent = buyrentrepo.findAll();
        return convertToDto(listBuyRent);
    }


    public List<buyRentDto> getSortedBuyRent(String searchQuery, String categoryFilter, String listingFilter, String priceFilter){
        // This method handles all the filtering + sorting so needs a lot of if statements
        // TODO: Find more efficient way to do this (if possible)
        List<buyRent> sortedBuyRentList;

        // Converts the entered input to the right format
        if(listingFilter.equals("Listing Filters")){
            // Inputs must be null for the query by example to ignore them
            listingFilter = null;
        }else if(listingFilter.equals("To Buy")){
            listingFilter = "0";
        }else{
            listingFilter = "1";
        }
        if(categoryFilter.equals("Category Filters")){
            // Inputs must be null for the query by example to ignore them
            categoryFilter = null;
        }

/*      This row of if statements is required as I can't find a way to alter the sorting order based on a
        variable with just using if statements */
        if(priceFilter.equals("Price Ascending")){
            // Creates a matcher and tells it what to ignore (this is required to initialise the query by example)
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withIgnoreNullValues()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            // Creates the query, required fields go in the new buyRent object
            Example<buyRent> createdQuery = Example.of(new buyRent(searchQuery ,categoryFilter, listingFilter), matcher);
            sortedBuyRentList = buyrentrepo.findBy(createdQuery,
                    q -> q
                            // Details the sorting order and by which field
                            .sortBy(Sort.by("price").ascending())
                            // returns all values instead of just the first etc..
                            .all());

        } else if(priceFilter.equals("Price Descending")) {
            // Same comments as above
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withIgnoreNullValues()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            Example<buyRent> createdQuery = Example.of(new buyRent(searchQuery ,categoryFilter, listingFilter), matcher);
            sortedBuyRentList = buyrentrepo.findBy(createdQuery,
                    q -> q
                            .sortBy(Sort.by("price").descending())
                            .all());
        }else{
            // Same comments as above
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withIgnoreNullValues()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            System.out.println("KEKW"+buyrentrepo.findAll());
            Example<buyRent> createdQuery = Example.of(new buyRent(searchQuery ,categoryFilter, listingFilter), matcher);
                sortedBuyRentList = buyrentrepo.findAll(createdQuery);
        }
        return convertToDto(sortedBuyRentList);
    }

    public List<buyRentDto> findItemById(Long itemId){
        /* Finds the object with a specified id, this return an Optional I was unable to find an easier way
        to do this even though there will only every be one object with each id
         */
        // TODO: If we have time research a better method of doing this
        Optional<buyRent> buyrentOpt = buyrentrepo.findById(itemId);
        List<buyRent> buyrent = buyrentOpt.stream().toList();
        return(convertToDto(buyrent));
    }

    public void sendEmailResponse(BuyRentEmailForm buyRentEmailForm){
        /* Finds the object with a specified id, this return an Optional I was unable to find an easier way
        to do this even though there will only every be one object with each id
         */
        Optional<buyRent> buyrentOpt = buyrentrepo.findById(buyRentEmailForm.getItemId());
        List<buyRent> buyrent = buyrentOpt.stream().toList();
        // Gets first object from the list (should only be one)
        var buyRentObject = buyrent.get(0);
        // Creates the new EmailService to send the email
        EmailService emailService = new EmailService(javaMailSender);
        // Content and fields for email
        String to = buyRentObject.getEmail();
        String subject = buyRentObject.getItemName();
        String body = (buyRentEmailForm.getMessage() + ". The sender email address is: " + buyRentEmailForm.getEmailAddress());
        // Sends email
        emailService.sendEmail(to, subject, body);
    }

    public BindingResult checkForProfanities(BuyRentForm buyRentForm, BindingResult bindingResult) {
        // Checks for profanities in the description
        GlobalValidation global = new GlobalValidation();
        List<FieldError> banned = global.Validation(buyRentForm.getItemName().toString(),"itemName","buyRentForm");
        banned.addAll(global.Validation(buyRentForm.getUsername().toString(),"username","buyRentForm"));
        banned.addAll(global.Validation(buyRentForm.getDescription().toString(),"description","buyRentForm"));

        for (int i=0;i<banned.size();i++){
            bindingResult.addError(banned.get(i));
        }
        if (bindingResult.hasErrors()) {
            System.out.println("LOG: User Error");
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        return bindingResult;
    }

    public void deleteItem(Long itemId){
        // Deletes the item with the specified id
        buyrentrepo.deleteById(itemId);
    }
}