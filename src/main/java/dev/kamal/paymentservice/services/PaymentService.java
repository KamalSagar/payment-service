package dev.kamal.paymentservice.services;

import com.stripe.exception.StripeException;
import dev.kamal.paymentservice.services.paymentgateways.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentGateway paymentGateway;

    PaymentService(@Qualifier("stripe") PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public String generatePaymentLink(String orderId, Long amount)
    throws StripeException {
        return paymentGateway.createPaymentLink(orderId, amount);
    }
}
