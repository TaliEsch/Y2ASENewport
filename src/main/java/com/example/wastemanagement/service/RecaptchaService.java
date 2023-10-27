package com.example.wastemanagement.service;

import com.example.wastemanagement.recaptcha.RecaptchaResponse;

public interface RecaptchaService {

    RecaptchaResponse verify(String response);
}
