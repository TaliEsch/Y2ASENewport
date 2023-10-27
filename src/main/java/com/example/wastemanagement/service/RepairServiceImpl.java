package com.example.wastemanagement.service;

import com.example.wastemanagement.data.RepairRepositoryJpa;
import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.domain.Repair;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl implements RepairService {
    private final RepairRepositoryJpa repairRepositoryJpa;
    private final JavaMailSender javaMailSender;
    private final HubRepositoryJpa hubRepository;
    private final HubService hubService;

    public RepairServiceImpl(RepairRepositoryJpa repairRepositoryJpa, JavaMailSender javaMailSender, HubRepositoryJpa hubRepository, HubService hubService) {
        this.repairRepositoryJpa = repairRepositoryJpa;
        this.javaMailSender = javaMailSender;
        this.hubRepository = hubRepository;
        this.hubService = hubService;
    }

    @Override
    public void saveBooking(Repair booking) {
        System.out.println("Booking details: "+booking);
        System.out.println(booking.getBookingId());
        repairRepositoryJpa.save(booking);
    }

    @Override
    public String[] sendEmail(Repair repair) {
        // get the hub details
        var hub = hubService.getHubByHubId(repair.getHubId());

        // email details
        EmailService emailService = new EmailService(javaMailSender);
        String to = repair.getEmail();
        String subject = "Booking confirmation";
        String body = "Booking successful at "+hub.getHubName()+" for "+repair.getItemName()+" at "+hub.getAddress();
        emailService.sendEmail(to, subject, body);

        // confirmation message
        String[] message = new String[] {"Booking successful for your "+repair.getItemName(),
                "At "+hub.getHubName()+": "+hub.getAddress(),
                "An email has been sent to "+repair.getEmail()};
        return message;
    }
}
