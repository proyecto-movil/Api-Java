package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.UserSubscription;
import com.example.ilenguageapi.domain.service.UserSubscriptionService;
import com.example.ilenguageapi.resource.SaveUserSubscriptionResource;
import com.example.ilenguageapi.resource.UserSubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Operation(summary="Assing user to suscription", description="Assing user to  suscription", tags = {"user subscriptions"})
    @PostMapping("/users/{userId}/subscription/{subscriptionId}")
    public UserSubscription assingUserToSubscription(@RequestParam(name="userId") int userId, @RequestParam(name="subscriptionId") int subscriptionId){
        return userSubscriptionService.assignUserSubscription(userId,subscriptionId);
    }

    @Operation(summary="Unassing user to suscription", description="Unassing user to  suscription", tags = {"user subscriptions"})
    @RequestMapping(value = "/users/{userId}/subscriptions", method = RequestMethod.PUT)
    public UserSubscription unassingUserSubscription(@RequestParam(name="userId") int userId){
        return userSubscriptionService.unassingUserSubscription(userId);
    }


    @Operation(summary="Get by use id", description="Get the subscription giving the user id related ", tags = {"user subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))
    })
    @RequestMapping(value ="/user/{userId}/subscriptions", method = RequestMethod.GET)
    public Page<UserSubscription> getUserSubscriptionByUserId(@PathVariable(name="userId") int userId,Pageable pageable){
        List<UserSubscription> userSubscriptionResourceList = new ArrayList<>(userSubscriptionService.getByUSerId(pageable, userId)
                .getContent());
        int subscriptionCount = userSubscriptionResourceList.size();
        return new PageImpl<>(userSubscriptionResourceList, pageable,subscriptionCount);

    }
}
