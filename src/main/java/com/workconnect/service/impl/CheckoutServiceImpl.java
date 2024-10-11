package com.workconnect.service.impl;

import com.workconnect.dto.PaymentCaptureResponse;
import com.workconnect.dto.PaymentOrderResponse;
import com.workconnect.dto.UserSubscriptionDTO;
import com.workconnect.integration.payment.paypal.dto.OrderCaptureResponse;
import com.workconnect.integration.payment.paypal.dto.OrderResponse;
import com.workconnect.integration.payment.paypal.service.PayPalService;
import com.workconnect.service.CheckoutService;
import com.workconnect.service.UserSubscriptionService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final UserSubscriptionService userSubscriptionService;
    //private final EmailService emailService;

    //@Value("${spring.mail.username}")
    //private String mailFrom;

    @Override
    public PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse =payPalService.createOrder(purchaseId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException{
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            UserSubscriptionDTO userSubscriptionDTO = userSubscriptionService.confirmUserSubscription(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setPurchaseId(userSubscriptionDTO.getId());

            //sendPurchaseConfirmationEmail(userSubscriptionDTO);

        }
        return paypalCaptureResponse;
    }

    /*private void sendPurchaseConfirmationEmail(UserSubscriptionDTO userSubscriptionDTO) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();


        Map<String, Object> model = new HashMap<>();
        model.put("user", userEmail);
        model.put("total", userSubscriptionDTO.getAmount());
        model.put("orderUrl", "http://localhost:4200/order/" + userSubscriptionDTO.getId());


        Mail mail = emailService.createMail(
                userEmail,
                "Confirmación de Compra",
                model,
                mailFrom
        );
        emailService.sendEmail(mail,"email/purchase-confirmation-template");
    }*/
}