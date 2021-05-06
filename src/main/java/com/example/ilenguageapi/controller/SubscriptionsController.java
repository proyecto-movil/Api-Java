package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private ModelMapper mapper;
}
