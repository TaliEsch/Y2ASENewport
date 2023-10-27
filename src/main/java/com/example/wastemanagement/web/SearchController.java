package com.example.wastemanagement.web;

import com.example.wastemanagement.domain.Category;
import com.example.wastemanagement.domain.Hub;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.HubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("hubs")
public class SearchController {
    // Creating an instance of the serve in the constructor
    private final CategoryService categoryService;
    private final HubService hubService;

    public SearchController(CategoryService categoryService, HubService hubService) {
        this.categoryService = categoryService;
        this.hubService = hubService;
    }

    @GetMapping("search")
    // @RequestParam: query parameters and map them to method parameters
    // optional parameter if the parameter is missing.
    public ModelAndView getCategories(@RequestParam(name = "q", required = false) String query,
                                      @RequestParam(name = "p", required = false) String postcode, Model model) {
        // gets the list of categories from the service
        var categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", query);

        // if the query is not null or All, then return all the categories that match the query
        if (!(query == null || query.equals("All"))) {
            // then return the categories that match the query
            List<Hub> categoryHub = hubService.getHubByCategoryAndPostcode(query, postcode);
            model.addAttribute("hubs", categoryHub);
            var mv = new ModelAndView("Search/category-list", model.asMap());
            return mv;
        }

        //Got the postcode from the user
        var hubs = hubService.getClosestHubsUsingPostcode(postcode);

        // creates a model and view object using the path of the template and the model as a map
        model.addAttribute("hubs", hubs);
        var mv = new ModelAndView("Search/category-list", model.asMap());
        return mv;
    }
}
