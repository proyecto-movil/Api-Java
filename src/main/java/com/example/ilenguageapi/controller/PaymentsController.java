package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.service.PaymentService;
import com.example.ilenguageapi.resource.SavePaymentResource;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody SavePaymentResource paymentResource) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentResource);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
}
