package com.example.wastemanagement.recaptcha;

import java.util.Map;

public class RecaptchaErrorCodes {

    // this function returns a map variable containing the errors and the messages for the recaptcha validation!
    public static final Map<String, String> RECAPTCHA_ERRORS = Map.of(
            "missing-input-secret", "The secret parameter is missing.",
            "invalid-input-secret", "The secret parameter is invalid.",
            "missing-input-response", "The response parameter is missing.",
            "invalid-input-response", "The response parameter is invalid.",
            "bad-request", "The request is invalid or malformed",
            "timeout-or-duplicate", "The response is no longer valid: either is too old or has been used previously."
    );
}
