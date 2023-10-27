package com.example.wastemanagement.web;

import com.example.wastemanagement.service.AccountService;
import com.example.wastemanagement.service.GlobalValidation;
import com.example.wastemanagement.web.Form.AdminCreationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@EnableWebSecurity
@Controller
@RequestMapping()
public class AdminAccountController {

    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    public AdminAccountController(AccountService accountService){
        this.accountService = accountService;
    }

    // This GET method sends the data model used for the form on the website.
    @GetMapping("/account-create")
    public ModelAndView getCreatePage(Model model){
        model.addAttribute("adminCreationForm", new AdminCreationForm());
        var modelView = new ModelAndView("account-create",model.asMap());
        return modelView;
    }

    // This POST method retrieves the AdminCreationForm and checks it's valid based on the annotations on AdminCreationForm.
    // It also retrieves any binding results nad a model.
    @PostMapping("/account-create")
    public ModelAndView addAccount(@Valid AdminCreationForm adminCreationForm, BindingResult bindingResult, Model model) throws IOException {

        // This makes a class variable to use the methods inside and a uses it in the variable banned to validate the username and password.
        GlobalValidation globalVal = new GlobalValidation();
        List<FieldError> banned = globalVal.Validation(adminCreationForm.getUsername(),"username","adminCreationForm");
        banned.addAll(globalVal.Validation(adminCreationForm.getPassword(),"password","adminCreationForm"));

        // Checks if the password and confirm password matches and if not, adds a field error to the banned variable.
        if(!adminCreationForm.getPassword().equals(adminCreationForm.getConfirmPassword())){
            banned.add(new FieldError(adminCreationForm.getPassword(),"password","Passwords do not match!"));
        }

        // Checks if there is an account with the username currently stored in database and if so, adds a field error to the list.
        if(accountService.findAccountCredentialsByAccountName(adminCreationForm.getUsername()) != null){
            System.out.println("User already exists!");
            banned.add(new FieldError(adminCreationForm.getUsername(),"username","User already exists!"));
        }

        // Puts all the errors within banned into binding results
        for (int i=0;i<banned.size();i++){
            bindingResult.addError(banned.get(i));
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + banned.toString());

        // Checks if there are any bindingResult errors and prints them out to the console.
        if(bindingResult.hasErrors()){
            System.out.println("LOG: User Error");
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        // Checks if the bindingResult doesn't have errors and if true, sends the data to be saved.
        if(!bindingResult.hasErrors()){
            accountService.addAccount(adminCreationForm);
        }

        var modelView = new ModelAndView();
        return modelView;
    }
}
