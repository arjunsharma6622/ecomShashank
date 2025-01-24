package com.shashank.ecom.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements PaymentService{

    @Override
    public String getPaymentLink(Long id, Double totalAmount, String email) throws StripeException {
        // create a priceObject - stripe

        Stripe.apiKey = "rk_test_51LJsV1SEnzmV5anHpXUKZMW4kIidHQEWJbZIVPhWU9GJY1N9gWGXaQyNCptUVBQ8oTuFJTKhj81BFtQR27wh4V6E00BWEKfVsg";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(Math.round(totalAmount * 100))
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(email).build()
                        )
                        .putMetadata("orderId", String.valueOf(id))
                        .build();

        Price price = Price.create(params);

        // using this priceObject create a payment link
        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("http://localhost:3000")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink;
        paymentLink = PaymentLink.create(paymentLinkParams);

        return paymentLink.getUrl();
    }
}
