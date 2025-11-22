package com.annvitra.annvitra.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;


import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

@Service
public class SMSservice {

    @Value("${twilio.account-sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth-token}")
    private String twilioAuthToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    public void sendOTP(String toPhoneNumber, String message) {
        // Logic to send SMS using Twilio or any other SMS service provider

        Twilio.init(twilioAccountSid, twilioAuthToken);
        String phoneNumber = "+" + toPhoneNumber;
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
               "Your OTP is: " + message
        ).create();
    }

}
