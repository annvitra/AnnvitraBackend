package com.annvitra.annvitra.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSservice {

    // @Value("${twilio.account-sid}")
    // private String twilioAccountSid;

    // @Value("${twilio.auth-token}")
    // private String twilioAuthToken;

    // @Value("${twilio.phone-number}")
    // private String twilioPhoneNumber;

    // public void sendOTP(String toPhoneNumber, String message) {
    // // Logic to send SMS using Twilio or any other SMS service provider

    // Twilio.init(twilioAccountSid, twilioAuthToken);
    // String phoneNumber = "+" + toPhoneNumber;
    // Message.creator(
    // new PhoneNumber(phoneNumber),
    // new PhoneNumber(twilioPhoneNumber),
    // "Your OTP is: " + message
    // ).create();
    // }

    @Value("${otp.api.key}")
    private String API_KEY;

    public String sendOTP2(String mobile10digit) throws Exception {
        String otp = String.format("%06d", new Random().nextInt(999999));
        // Normalize incoming number: keep digits only
        String digits = mobile10digit == null ? "" : mobile10digit.replaceAll("\\D", "");
        if (digits.length() == 10) {
            // assume local Indian number, prefix country code without + for 2factor API
            digits = "91" + digits;
        } else if (digits.length() == 12 && digits.startsWith("91")) {
            // already contains country code
        } else {
            // best-effort: leave as-is (may fail at provider)
            System.out
                    .println("Warning: mobile number not 10 or 12 digits: '" + mobile10digit + "' -> '" + digits + "'");
        }
        String mobile = digits;

        // Force SMS-only (no voice fallback)
        String urlString = "https://2factor.in/API/V1/" + API_KEY + "/SMS/" + mobile + "/" + otp + "?method=SMS";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                responseCode == 200 ? con.getInputStream() : con.getErrorStream()));
        String response = br.lines().reduce("", (a, b) -> a + b);
        br.close();

        System.out.println("OTP Attempt: " + otp + " → " + mobile10digit);
        System.out.println("Status: " + responseCode);
        System.out.println("Response: " + response);

        return otp;
    }

    public String sendOTP(String mobile) {
        System.out.println("Forcing SMS-Only via 2Factor.in...");
        try {
            return sendOTP2(mobile);
        } catch (Exception e) {
            System.out.println("Failed to send OTP via 2Factor.in:");
            e.printStackTrace();
            return null;
        }
    }
}
