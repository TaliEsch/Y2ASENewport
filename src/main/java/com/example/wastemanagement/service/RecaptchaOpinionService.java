package com.example.wastemanagement.service;


import com.example.wastemanagement.recaptcha.RecaptchaKeys;
import com.example.wastemanagement.recaptcha.RecaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ResponseCache;
import java.net.URI;

@Service
public class RecaptchaOpinionService implements RecaptchaService {

    public final RecaptchaKeys recaptchaKeys;
    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(RecaptchaOpinionService.class);

    // This class function is to store the data within and also allow the use of Logger.
    @Autowired
    public RecaptchaOpinionService(RecaptchaKeys recaptchaKeys, RestTemplate restTemplate){
        this.recaptchaKeys = recaptchaKeys;
        this.restTemplate = restTemplate;
    }

    // This function is used to verify the captcha by receiving the response from Google and checking if it was a success and if the threshold was within the parameters set.
    @Override
    public RecaptchaResponse verify(String response){

        URI verifyURI = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", recaptchaKeys.getSecret(), response));

        RecaptchaResponse recaptchaResponse = restTemplate.getForObject(verifyURI, RecaptchaResponse.class);

        log.info("/////////////////////// response verified : {}", recaptchaResponse);

        if(recaptchaResponse != null && recaptchaResponse.getAction() != null){
            if(recaptchaResponse.isSuccess() && (recaptchaResponse.getScore() < recaptchaKeys.getThreshold() || !recaptchaResponse.getAction().equals("submit"))){
                recaptchaResponse.setSuccess(false);
            }
        }

        return recaptchaResponse;
    }
}
