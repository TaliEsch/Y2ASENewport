package com.example.wastemanagement.web;

import com.example.wastemanagement.domain.Repair;
import com.example.wastemanagement.service.HubService;
import com.example.wastemanagement.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookingController {
    private final RepairService repairService;
    private final HubService hubService;

    public BookingController(RepairService repairService, HubService hubService) {
        this.repairService = repairService;
        this.hubService = hubService;
    }

    @GetMapping("")
    public ModelAndView booking(Model model) {
        var tips = hubService.getHubsByR("tip");
        model.addAttribute("tips", tips);
        var mv = new ModelAndView("book/booking", model.asMap());
        return mv;
    }

    @GetMapping("/repair")
    public ModelAndView repair(Model model) {
        var repairHubs = hubService.getHubsByR("repair");
        model.addAttribute("hubs", repairHubs);
        model.addAttribute("repair", new Repair());
        var mv = new ModelAndView("book/repair", model.asMap());
        return mv;
    }

//    email needs validation

    @PostMapping("/repair")
    // gets the booking object from the form
    public ModelAndView repairbooking(Model model, @Valid Repair repair, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var repairHubs = hubService.getHubsByR("repair");
            model.addAttribute("hubs", repairHubs);
            var mv = new ModelAndView("book/repair", model.asMap());
            return mv;
        }
        // saves the booking to the database
        repairService.saveBooking(repair);

        // sends an email to the user and send back a confirmation message
        String[] message = repairService.sendEmail(repair);

        model.addAttribute("message", message);
        model.addAttribute("repair", repair);
        var mv = new ModelAndView("book/repair", model.asMap());
        return mv;
    }

}
