package dev.kamal.paymentservice.controllers;


import com.stripe.exception.StripeException;
import dev.kamal.paymentservice.dtos.InitiatePaymentDto;
import dev.kamal.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> initiatePayment(@RequestBody InitiatePaymentDto requestDto)
    throws StripeException {
        String paymentLink = paymentService.generatePaymentLink(requestDto.getOrderId(), requestDto.getAmount());
        return ResponseEntity.ok(paymentLink);
    }

}
