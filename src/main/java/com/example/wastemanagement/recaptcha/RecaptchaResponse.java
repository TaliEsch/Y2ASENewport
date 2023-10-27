package com.example.wastemanagement.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;


// This class contains all the information received from the Google recaptcha, so they can be used later on.
public class RecaptchaResponse {

    private boolean success;
    private float score;
    private String action;
    @JsonProperty("challenge_ts")
    private String challengeTs;
    private String hostname;
    @JsonProperty("error-codes")
    List<String> errors;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChallengeTs() {
        return challengeTs;
    }

    public void setChallengeTs(String challengeTs) {
        this.challengeTs = challengeTs;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<String> getErrorsCodes() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors(){
        if(getErrorsCodes() != null){
            return getErrorsCodes().stream()
                    .map(RecaptchaErrorCodes.RECAPTCHA_ERRORS::get)
                    .collect(Collectors.toList());
        }
        return null;
    }


    // This returns the response of the recaptcha verification as a string format.
    @Override
    public String toString() {
        return "RecaptchaResponse{" +
                "success=" + success +
                ", score=" + score +
                ", action='" + action + '\'' +
                ", challengeTs='" + challengeTs + '\'' +
                ", hostname='" + hostname + '\'' +
                ", errors=" + errors +
                '}';
    }
}
