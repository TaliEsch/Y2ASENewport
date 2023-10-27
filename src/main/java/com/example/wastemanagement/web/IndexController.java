package com.example.wastemanagement.web;


import com.example.wastemanagement.service.indexService;
import com.example.wastemanagement.web.Form.indexForm;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/")
public class IndexController {

    private indexService iS;

    @Autowired
    public IndexController(indexService iS) {
        this.iS = iS;
    }

    @GetMapping("")
    public ModelAndView indexHP(Model model) {
        var index = iS.getIndex();
        model.addAttribute("index", index);
        var modelView = new ModelAndView("index", model.asMap());
        return modelView;
    }

    @PostMapping("")
    public ModelAndView indexSave(@Valid indexForm iF, BindingResult bindingResult, Model model) throws IOException {
        bindingResult = (iS.checkForProfanities(iF, bindingResult));
        if (bindingResult.hasErrors()) {
            model.addAttribute("indexForm", iF);
            var modelView = new ModelAndView("admin/index-form", model.asMap());
            return modelView;
        }
        iS.addIndex(iF);
        var index = iS.getIndex();
        model.addAttribute("index", index);
        var modelView = new ModelAndView("index", model.asMap());
        return modelView;
    }
}
