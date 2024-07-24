package dev.kamal.paymentservice.services.paymentgateways;

import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentGateway{

    @Override
    public String createPaymentLink(String orderId, Long amount) {
        return "";
    }
}
