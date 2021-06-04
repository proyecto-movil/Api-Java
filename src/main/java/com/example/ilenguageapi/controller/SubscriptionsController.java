package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.service.SubscriptionService;
import com.example.ilenguageapi.resource.SaveSubscriptionResource;
import com.example.ilenguageapi.resource.SubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private ModelMapper mapper;

    private Subscription convertToEntity(SaveSubscriptionResource resource){
        return mapper.map(resource, Subscription.class);
    }
    private SubscriptionResource convertToResource(Subscription entity){
        return mapper.map(entity, SubscriptionResource.class);
    }

    @Operation(summary="Get all", description="Get all the subscriptions", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/subscriptions")
    public Page<SubscriptionResource> getAllSubscriptions(Pageable pageable){
        List<SubscriptionResource> subscriptions = subscriptionService.getAllSubscriptions(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int subscriptionCount = subscriptions.size();
        return new PageImpl<>(subscriptions, pageable, subscriptionCount);
    }

    @Operation(summary="Save suscription", description="Save suscription", tags = {"subscriptions"} )
    @PostMapping("/subscriptions")
    public SubscriptionResource createSubscription(@Valid @RequestBody SaveSubscriptionResource resource){
        return convertToResource(subscriptionService.createSubscription(convertToEntity(resource)));
    }

    @Operation(summary="Update suscription", description="Update suscription", tags = {"subscriptions"} )
    @PutMapping ("subscriptions/{id}")
    public SubscriptionResource updateSubscription(@PathVariable(name="id") int subscriptionId, @Valid @RequestBody SaveSubscriptionResource resource){
        return convertToResource(subscriptionService.updateSubscription(subscriptionId,convertToEntity(resource)));
    }

    @Operation(summary="Delete suscription", description="Delete suscription", tags = {"subscriptions"} )
    @DeleteMapping("subscriptions/{id}")
    public ResponseEntity<?> deleteSubscriptions(@PathVariable(name="id") int subscriptionId){
        return subscriptionService.deleteSubscription(subscriptionId);
    }


    @Operation(summary="Get by id", description="Get the subscription giving an id", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value ="subscriptions/{id}", method = RequestMethod.GET)
    public SubscriptionResource getSubscriptionById(@PathVariable(name="id") int subscriptionId){
        return convertToResource(subscriptionService.getBySubscriptionId(subscriptionId));
    }

    @Operation(summary="Get by name", description="Get the subscription giving a name", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value = "/subscriptions/name", method = RequestMethod.GET)
    public SubscriptionResource getSubscriptionByName(@RequestParam(name="name") String subscriptionName){
        return convertToResource(subscriptionService.getByName(subscriptionName));
    }

    @Operation(summary="Get by price", description="Get the subscription giving the price", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value ="/subscriptions/price", method = RequestMethod.GET)
    public SubscriptionResource getSubscriptionByPrice(@RequestParam(name="price") int producedPrice){
        return convertToResource(subscriptionService.getByPrice(producedPrice));
    }

    @Operation(summary="Get by month duration", description="Get the subscription giving the month duration ", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All subscriptions returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Subscription Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value = "/subscriptions/monthduration", method = RequestMethod.GET)
    public SubscriptionResource getSubscriptionByMonthDuration(@RequestParam(name="monthDuration") int subscriptionMonthDuration){
    return convertToResource(subscriptionService.getByDuration(subscriptionMonthDuration));
    }


}
