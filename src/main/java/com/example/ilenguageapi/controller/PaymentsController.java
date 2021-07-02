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
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody SavePaymentResource paymentResource) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentResource);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }


    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirmePayment(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirmPaymentIntent(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }


    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancelPaymentIntent(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }


}
