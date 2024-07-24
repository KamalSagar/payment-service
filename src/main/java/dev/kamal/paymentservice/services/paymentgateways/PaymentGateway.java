package dev.kamal.paymentservice.services.paymentgateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    public String createPaymentLink(String orderId, Long amount) throws StripeException;
}
