package com.example.ilenguageapi.unittest;

import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.repository.SubscriptionRepository;
import com.example.ilenguageapi.domain.service.SubscriptionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.SubscriptionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceImplTest {
    @MockBean
    private SubscriptionRepository _subscriptionRepository;
    @Autowired
    private SubscriptionService _subscriptionService;

    @TestConfiguration
    static class SubscriptionServiceImplConfiguration{
        @Bean
        public SubscriptionService subscriptionService(){return new SubscriptionServiceImpl();}
    }

    @Test
    @DisplayName("Get subscription by name with valid name then return true")
    public void whenGetSubscriptionByNameWithValidNameThenReturnsSubscription(){
        //Arrange
        String name = "FullPass";
        Subscription subscription = new Subscription();
        subscription.setName(name);
        subscription.setId(45);
        when(_subscriptionRepository.findByName(name))
                .thenReturn(Optional.of(subscription));
        //Act
        Subscription foundSubscription = _subscriptionService.getByName(name);

        //Assert
        assertThat(foundSubscription.getName()).isEqualTo(name);
    }


    @Test
    @DisplayName("Get subscription by price with valid price then return true")
    public void whenGetSubscriptionByPriceWithValidPriceThenReturnsSubscription(){
        //Arrange
        int price = 99;
        Subscription subscription = new Subscription();
        subscription.setPrice(price);
        subscription.setId(45);
        when(_subscriptionRepository.findByPrice(price))
                .thenReturn(Optional.of(subscription));
        //Act
        Subscription foundSubscription = _subscriptionService.getByPrice(price);

        //Assert
        assertThat(foundSubscription.getPrice()).isEqualTo(price);
    }


    @Test
    @DisplayName("Get subscription by mothDuration with valid month then return true")
    public void whenGetSubscriptionByMonthWithValidMonthThenReturnsSubscription(){
        //Arrange
        int month = 8;
        Subscription subscription = new Subscription();
        subscription.setMonthDuration(month);
        subscription.setId(45);
        when(_subscriptionRepository.findByMonthDuration(month))
                .thenReturn(Optional.of(subscription));
        //Act
        Subscription foundSubscription = _subscriptionService.getByDuration(month);

        //Assert
        assertThat(foundSubscription.getMonthDuration()).isEqualTo(month);
    }

    @Test
    @DisplayName("Get subscription error message when name is not valid")
    public void whenGetSubscriptionByNameWithInvalidNameReturnsResourceNotFoundExeption(){
        String name ="Basic";
        String template ="Resource %s not found for %s with value %s";
        when(_subscriptionRepository.findByName(name))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Subscription", "Name", name);
        //Act
        Throwable exception = catchThrowable(()->{
                Subscription foundSubscription = _subscriptionService.getByName(name);
        });
        //Asert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
