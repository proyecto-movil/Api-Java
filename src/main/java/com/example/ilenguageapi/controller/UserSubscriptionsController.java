package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.UserSubscription;
import com.example.ilenguageapi.domain.service.UserSubscriptionService;
import com.example.ilenguageapi.resource.SaveUserSubscriptionResource;
import com.example.ilenguageapi.resource.UserSubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserSubscriptionsController {
    @Autowired
    private UserSubscriptionService userSubscriptionService;
    @Autowired
    private ModelMapper modelMapper;

    private UserSubscription convertToEntity(SaveUserSubscriptionResource resource){
        return modelMapper.map(resource, UserSubscription.class);
    }
    private UserSubscriptionResource convertToResource(UserSubscription entity) {
        return modelMapper.map(entity,UserSubscriptionResource.class);
    }

    @Operation(summary="Assing user to suscription", description="Assing user to  suscription", tags = {"userSubscriptions"})
    @PostMapping("/subacriptions/{subscriptionId}/users/userId")
    public UserSubscription assingUserToSubscription(@RequestParam(name="subscriptionId") int subscriptionId, @RequestParam(name="userId") int userId){
        return userSubscriptionService.assignUserSubscription(userId,subscriptionId);
    }

}
