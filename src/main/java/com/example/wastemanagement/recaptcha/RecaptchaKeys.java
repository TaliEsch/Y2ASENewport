package com.example.wastemanagement.recaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// This class contains the site key and the secret key as google captcha uses asymmetric encryption and also stores the threshold required!
// The data of these variables are contained within application-dev.properties.
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class RecaptchaKeys {

    String site;
    private String secret;
    private float threshold;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public float getThreshold() {
        return threshold;
    }

}
