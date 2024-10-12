package com.workconnect.service;

import com.workconnect.dto.PaymentCaptureResponse;
import com.workconnect.dto.PaymentOrderResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl) throws MessagingException;

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}