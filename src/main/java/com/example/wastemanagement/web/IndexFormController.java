package com.example.wastemanagement.web;

import com.example.wastemanagement.service.indexService;
import com.example.wastemanagement.web.Form.indexForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class IndexFormController {
    private final indexService iS;


    public IndexFormController(indexService iS) {
        this.iS = iS;

    }

    @GetMapping("/index/save")
    public ModelAndView indexSave(Model model) {

        model.addAttribute("indexForm", new indexForm());
        var modelView = new ModelAndView("admin/index-form", model.asMap());
        return modelView;
    }


}
