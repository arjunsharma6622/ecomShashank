package com.shashank.ecom.controllers;

import com.shashank.ecom.Exceptions.OrderNOtFoundException;
import com.shashank.ecom.Services.PaymentService;
import com.shashank.ecom.models.Order;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/payment/webhook")
    public void handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signatureHeader
    ){
        try{
            Event event = Webhook.constructEvent(payload, signatureHeader, "whsec_c6ebc1426a1e4b70bf788a21f61aac7d686094f0be41c45d6b4c8784f060fb13");
            System.out.println(event.getType());
        }
        catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }

}
