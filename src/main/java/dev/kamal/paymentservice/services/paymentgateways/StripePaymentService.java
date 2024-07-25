package dev.kamal.paymentservice.services.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentGateway{
    @Value("${stripe.api.secret}")
    private String stripeApiSecret;

    @Override
    public String createPaymentLink(String orderId, Long amount)
            throws StripeException {
        Stripe.apiKey = stripeApiSecret;

        PriceCreateParams priceparams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Order Id: "+orderId).build()
                        )
                        .build();

        Price price = Price.create(priceparams);

        String paymentSuccessUrl = "http://prodservbykamal.me:8081/";
        PaymentLinkCreateParams params =
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
                                                        .setUrl(paymentSuccessUrl)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        System.out.println(paymentLink.getUrl());
        return paymentLink.getUrl();
    }
}
