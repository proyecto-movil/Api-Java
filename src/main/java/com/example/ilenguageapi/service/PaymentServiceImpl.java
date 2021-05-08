package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.service.PaymentService;
import com.example.ilenguageapi.resource.SavePaymentResource;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    //reference
    //https://stripe.com/docs/api/payment_intents/confirm
    @Value("$[stripe.key.secret]")
    String secretKey;


    @Override
    public PaymentIntent paymentIntent(SavePaymentResource paymentResource) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentResource.getAmount());
        params.put("currency", paymentResource.getCurrency());
        params.put("description", paymentResource.getDescription());
        ArrayList paymentMethodType = new ArrayList<>();
        paymentMethodType.add("card");
        params.put("payment_method_type", paymentMethodType);
        return PaymentIntent.create(params);
    }

    @Override
    public PaymentIntent confirmPaymentIntent(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
        return paymentIntent;
    }

    @Override
    public PaymentIntent cancelPaymentIntent(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }
}
