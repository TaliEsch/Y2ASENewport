package com.example.wastemanagement.web;

import com.example.wastemanagement.web.Form.InputForm;
import com.example.wastemanagement.recaptcha.RecaptchaResponse;
import com.example.wastemanagement.service.GlobalValidation;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.example.wastemanagement.service.RecaptchaOpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@EnableWebSecurity
@Controller
@RequestMapping("/councilInput")
public class WasterFormController {


    private final RecaptchaOpinionService recaptchaOpinionService;
    private static final Logger log = LoggerFactory.getLogger(WasterFormController.class);

    @Autowired
    public WasterFormController(RecaptchaOpinionService recaptchaOpinionService){
        this.recaptchaOpinionService = recaptchaOpinionService;
    }

    // This function is used for GET requests from /councilInput to retrieve the form data.
    @GetMapping()
    public ModelAndView getNewCouncilForm(Model model){
        model.addAttribute("inputForm", new InputForm());
        var mv = new ModelAndView("councilInput", model.asMap());
        return mv;
    }

    @Autowired
    RestTemplate restTemplate;

/*    This function is a POST mapping to receive the data from the webpage so that it can verify the data that has been
    sent */
    @PostMapping()
    public String processNewCouncilForm(@Valid InputForm newInput, BindingResult bindingResult, Model model, @RequestParam(name = "g-recaptcha-response") String response, Model recaptchaModel) throws IOException {
        log.info(">>>>>>>>>>>>>> captcha response: {}", response);
        RecaptchaResponse recaptchaResponse = recaptchaOpinionService.verify(response);

        if(!recaptchaResponse.isSuccess()){
            recaptchaModel.addAttribute("recaptchaError", recaptchaResponse.getErrors());
            return "/councilInput";
        }

        GlobalValidation global = new GlobalValidation();
        List<FieldError> banned = global.Validation(newInput.getOpinion().toString(),"opinion","inputForm");
        for (int i=0;i<banned.size();i++){
            bindingResult.addError(banned.get(i));
        }
        if (bindingResult.hasErrors()) {
            System.out.println("LOG: User Error");
            bindingResult.getAllErrors().forEach(System.out::println);
        }
        return ("councilInput");
    }
}
