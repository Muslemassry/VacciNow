package com.vaccnow.healthportal.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaccnow.healthportal.reponse.FawryResponse;

public class PaymentUtil {

	public static FawryResponse doPayment() throws UnsupportedEncodingException, IOException {
		String urlString = "https://developer.fawrystaging.com/api/testCreatePaymentAtFawry?merchantCode=1tSa6uxz2nTwlaAmt38enA==&customerMobile=01000000000&customerEmail=test@email.com&customerProfileId=777777&merchantRefNum=2312465464&amount=350.75&paymentExpiry=1631138400000&currencyCode=EGP&description=Example Description&language=en-gb&chargeItems[0][itemId]=897fa8e81be26df25db592e81c31c&chargeItems[0][description]=Item Descriptoin&chargeItems[0][price]=350.75&chargeItems[0][quantity]=1&signature=2ca4c078ab0d4c50ba90e31b3b0339d4d4ae5b32f97092dd9e9c07888c7eef36&paymentMethod=PAYATFAWRY";
		URL url = new URL (urlString);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> s = restTemplate.getForEntity(url+"", String.class);
		JSONParser parser = new JSONParser(s.getBody());
		FawryResponse fawryResponse = new ObjectMapper().readValue(s.getBody(), FawryResponse.class); 
		return fawryResponse;

	}
}
