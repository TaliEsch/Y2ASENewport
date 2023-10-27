package com.example.wastemanagement.web;

import com.example.wastemanagement.service.Dto.buyRentDto;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.BuyRentService;
//import com.example.wastemanagement.web.Advice.FileUploadExceptionAdvice;
import com.example.wastemanagement.web.Form.BuyRentEmailForm;
import com.example.wastemanagement.web.Form.BuyRentForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("buyRent")
public class buyRentController {

    private final BuyRentService buyrentservice;
    private final CategoryService categoryService;

    public buyRentController(BuyRentService buyrentservice, CategoryService categoryService){
        this.buyrentservice = buyrentservice;
        this.categoryService = categoryService;
    }

// May need to edit name
    @GetMapping("/save")
    public ModelAndView displayCats(Model model){

        // making types of purchases with its values as a map
        String[] purchaseTypes = {"Sell", "Rent - Per Week"};

        model.addAttribute("purchaseTypes", purchaseTypes);
        var categoryList = categoryService.getCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("buyRentForm", new BuyRentForm());
        var modelView = new ModelAndView("buyRent/buyRent", model.asMap());
        return modelView;
    }

    @PostMapping("/save")
    public ModelAndView buyRentSave(@Valid BuyRentForm buyRentForm, BindingResult bindingResult, Model model) throws IOException {
        bindingResult=(buyrentservice.checkForProfanities(buyRentForm, bindingResult));
        if(bindingResult.hasErrors()){
            String[] purchaseTypes = {"Sell", "Rent - Per Week"};
            model.addAttribute("purchaseTypes", purchaseTypes);
            var categoryList = categoryService.getCategories();
            model.addAttribute("categories", categoryList);
            model.addAttribute("buyRentForm", buyRentForm);
            var modelView = new ModelAndView("buyRent/buyRent", model.asMap());
            return modelView;
        }

        buyrentservice.addBuyRent(buyRentForm);
        var modelView = new ModelAndView("redirect:/buyRent", model.asMap());
        return modelView;
    }

    // Below is currently redundant but kept encase its needed

/*    @PostMapping("/save")
    public String buyRentSave(BuyRentForm buyRent) throws IOException {
        buyrentservice.addBuyRent(buyRent);
        return("buyRent");
    }*/

    @GetMapping("")
    public ModelAndView buyRentDisplay(@RequestParam(name = "sq", required = false) String searchQuery,
                                       @RequestParam(name = "cf", required = false) String categoryFilter,
                                       @RequestParam(name = "lf", required = false) String listingFilter,
                                       @RequestParam(name = "pf", required = false) String priceFilter, Model model){

        // TODO: Need to move logic to service layer
        List<buyRentDto> allBuyRent;
        // Dropdown Names for Listing Filter  - Not stored as it's not worth the call to the database
        List<String> listingFilterList = Arrays.asList("To Buy", "To Rent");
        model.addAttribute("listingFilter", listingFilterList);
        // Dropdown Names for Price Filter
        List<String> priceFilterList = Arrays.asList("Price Ascending", "Price Descending");
        model.addAttribute("priceFilter", priceFilterList);
        // Return all categories to populate the dropdown
        var categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        // Sets is Empty to nothing, to be sent if anything is recieved
        model.addAttribute("isEmpty", "");

        // Checks if any of the values are empty, to make sure the filtering isn't run unnecessarily
        if(!(categoryFilter == null || listingFilter == null || priceFilter == null || searchQuery == null)){
            // Provides currently selected categoryFilter
            if (!categoryFilter.equals("Category Filters") || !listingFilter.equals("Listing Filters")
                    || !priceFilter.equals("Price Filters") || !searchQuery.equals("Search")){
//                // Check if the category is a category from the list of categories
//                if (((categories.contains(categoryFilter) && (listingFilterList.contains(listingFilter))
//                        && (priceFilterList.contains(priceFilter))))) {


                // Provides currently selected categoryFilter
                model.addAttribute("selectedCategory", categoryFilter);
                // Provides currently selected listingFilter
                model.addAttribute("selectedListing", listingFilter);
                // Provides currently selected priceFilter
                model.addAttribute("selectedPrice", priceFilter);
                // Provides current searchQuery
                model.addAttribute("searchQuery", searchQuery);
                // Returns sorted content
                allBuyRent = buyrentservice.getSortedBuyRent(searchQuery, categoryFilter, listingFilter, priceFilter);

                if(allBuyRent.size() == 0){
                    // Sets isEmpty to null to display error message if no items are returned
                    model.addAttribute("isEmpty", null);
                }

                System.out.println("filtering");
//                } else {
//                    // Returns all content
//                    allBuyRent = buyrentservice.getAllBuyRent();
//                }
            } else {
                // Return all content unfiltered
                allBuyRent = buyrentservice.getAllBuyRent();
            }
        }else {
            // Return all content unfiltered
            allBuyRent = buyrentservice.getAllBuyRent();
        }
//        model.addAttribute("buyRentForm", new BuyRentForm());
        model.addAttribute("allBuyRent", allBuyRent);
        return new ModelAndView("buyRent/buyRentDisplay", model.asMap());
    }

    @PostMapping("")
    public ModelAndView buyRentDisplay(Model model){
        // Change content based on filters
        return new ModelAndView("buyRent/buyRentDisplay", model.asMap());
    }

    @GetMapping("/item/{itemId}")
    public ModelAndView specificItemDisplay(@PathVariable("itemId") Long itemId, Model model){
        // Takes in the id of the current item, then returns the object with said id
        var buyRentItem = buyrentservice.findItemById(itemId);
        // Provides returned object
        model.addAttribute("allBuyRent", buyRentItem);

        // Creates new form to capture the entered message as well as to transfer the current itemId
        BuyRentEmailForm buyRentEmailForm = new BuyRentEmailForm();
        // TODO: CHECK WHY THIS IS OVERWRITTEN
        buyRentEmailForm.setItemId(itemId);
        model.addAttribute("buyRentEmailForm", buyRentEmailForm);
        return new ModelAndView("buyRent/buyRentItem", model.asMap());
    }

    @PostMapping("/item/response")
    public String redirectFromItem(BuyRentEmailForm buyRentEmailForm){
        // Sends the mail to the client then redirects them to the main buyRent page
        buyrentservice.sendEmailResponse(buyRentEmailForm);
        // TODO: Couldn't get other redirect methods to work, need to find a better solution
        return("redirect:http://localhost:8081/buyRent");
    }

    @PostMapping("delete/{itemId}")
    public RedirectView deleteItem(@PathVariable("itemId") Long itemId, RedirectAttributes redirectAttributes){
        buyrentservice.deleteItem(itemId);
        return new RedirectView("/buyRent");
    }

}
