package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Payment;
import com.example.ilenguageapi.resource.SavePaymentResource;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public interface PaymentService {

    public PaymentIntent paymentIntent(SavePaymentResource paymentResource) throws StripeException;
    public PaymentIntent confirmPaymentIntent(String id) throws StripeException;
    public PaymentIntent cancelPaymentIntent(String id) throws StripeException;
}
