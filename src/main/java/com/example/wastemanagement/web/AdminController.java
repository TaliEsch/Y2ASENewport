package com.example.wastemanagement.web;

import com.example.wastemanagement.domain.Hub;
import com.example.wastemanagement.service.AccountService;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.HubService;
import com.example.wastemanagement.web.Form.AdminLoginForm;
import com.example.wastemanagement.web.Form.HubForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@EnableWebSecurity
@Controller
@RequestMapping("admin")
public class AdminController {

    private final HubService hubService;
    private final CategoryService categoryService;

    private final AccountService accountService;

    @Autowired
    public AdminController(HubService hubService, CategoryService categoryService, AccountService accountService) {
        this.hubService = hubService;
        this.categoryService = categoryService;
        this.accountService = accountService;
    }


    /* Handles GET requests to admin/login, also provides a new AdminLoginForm object
    * to hold the inputted username and password */
    @GetMapping("login")
    public ModelAndView getLoginPage(Model model){
        model.addAttribute("adminLoginForm", new AdminLoginForm());
        var modelView = new ModelAndView("admin/login", model.asMap());
        return modelView;
    }

    /* Handles POST requests to admin/login, uses the AdminLoginForm object created
    * during the initial GET request checking the entered username and password against
    * those stored in variables. This is an initial test implementation, proper implementation
    * will be added in the #SpringBoot user story */
    @PostMapping("login")
    public String verifyLoginForm(AdminLoginForm adminLoginForm){
        return ("login");

/*
        if (adminLoginForm.getUsername().equals("1") && adminLoginForm.getPassword().equals("2")) {
            System.out.println("Hello 1!");
            return ("/admin/home");
        }else {
            System.out.println("Hello 2!");
            return ("/admin/login");
        }
*/
    }

    @GetMapping("home")
    public ModelAndView getAdminHomePage(Model model){
        var modelView = new ModelAndView("admin/homepage", model.asMap());
        return modelView;
    }

    @GetMapping("hub/add")
    public ModelAndView getAddHubForm(Model model){
        var categories =categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("hubForm", new HubForm());
        var modelView = new ModelAndView("admin/hub-form", model.asMap());
        return modelView;
    }

    @PostMapping("hub/add")
    public ModelAndView verifyAddHubForm(@Valid HubForm hubForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("hubForm", hubForm);
            var categories =categoryService.getCategories();

            model.addAttribute("categories", categories);
            var modelView = new ModelAndView("admin/hub-form", model.asMap());
            return modelView;
        }

        System.out.println(hubForm+"hubForm");
        // converts and add the hubForm to the database
        Hub hub = hubService.convertHubFormToHub(hubForm,null);
        hubService.saveHub(hub);

        // Shows the hubs
        List<Hub> hubs = hubService.getHubs();
        model.addAttribute("hubs", hubs);
        var modelView = new ModelAndView("admin/hubs", model.asMap());
        return modelView;
    }

    @GetMapping("hubs")
    public ModelAndView getHubsPage(Model model){
        List<Hub> hubs = hubService.getHubs();
        model.addAttribute("hubs", hubs);
        var modelView = new ModelAndView("admin/hubs", model.asMap());
        return modelView;
    }

    @GetMapping("/hub/{hubId}/edit")
    public ModelAndView getEditHubPage(Model model, @PathVariable("hubId") int hubId){
        // Get the hub (this is required as it stores the hubId, which hubForm doesn't carry)
        Hub hub = hubService.getHubByHubId(hubId);
        model.addAttribute("hub", hub);
        // Gets a hubForm that's already filled with the current info for that hub
        HubForm hubForm = hubService.getHubFormById(hubId);
        model.addAttribute("hubForm", hubForm);

        var categories =categoryService.getCategories();
        model.addAttribute("categories", categories);
        var modelView = new ModelAndView("admin/hub-edit", model.asMap());
        return modelView;
    }

    @PostMapping("/hub/{hubId}/edit")
    public ModelAndView getEditHubPage(Model model, @PathVariable("hubId") int hubId,@Valid HubForm hubForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            hubForm.setOtherCategoriesList(hubService.getUnselectedCategoryList(hubForm.getCategoriesList()));
            model.addAttribute("hubForm", hubForm);

            // Gets the current hub (this is required as it stores the hubId, which hubForm doesn't carry)
            Hub hub = hubService.getHubByHubId(hubId);
            model.addAttribute("hub", hub);

            var categories =categoryService.getCategories();
            model.addAttribute("categories", categories);

            return new ModelAndView("admin/hub-edit", model.asMap());
        }

        // updating the hub
        Hub hub = hubService.convertHubFormToHub(hubForm, hubId);
        hubService.saveHub(hub);

        List<Hub> hubs = hubService.getHubs();
        model.addAttribute("hubs", hubs);
        var modelView = new ModelAndView("admin/hubs", model.asMap());
        return modelView;
    }

    @PostMapping("/hub/{hubId}/delete")
    public RedirectView deleteHub(@PathVariable("hubId") int hubId, RedirectView redirectView){
        hubService.deleteHub(hubId);

        // redirecting to the hubs page
//        model.addAttribute("hubs", hubService.getHubs());
        return new RedirectView("/admin/hubs");
    }

    @PostMapping("/logout")
    public void logout() {
    }
}

