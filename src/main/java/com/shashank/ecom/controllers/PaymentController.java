package com.shashank.ecom.controllers;

import com.shashank.ecom.Exceptions.OrderNOtFoundException;
import com.shashank.ecom.Services.PaymentService;
import com.shashank.ecom.models.Order;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {

    private PaymentService paymentService;
    private final RestTemplate restTemplate;

    public PaymentController(PaymentService paymentService, RestTemplate restTemplate){
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/{orderId}")
    public String makePayment(@PathVariable("orderId") Long orderId) throws OrderNOtFoundException, StripeException {

        Order orderFromDB =  restTemplate.getForObject("http://localhost:8080/order/"+orderId, Order.class);

        String paymentLink;
        paymentLink = paymentService.getPaymentLink(orderId, orderFromDB.getPrice(), orderFromDB.getUser().getEmail());

        return paymentLink;
    }

}
