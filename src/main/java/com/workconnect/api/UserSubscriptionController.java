package com.workconnect.api;

import com.workconnect.dto.UserSubscriptionCreateUpdateDTO;
import com.workconnect.dto.UserSubscriptionDTO;
import com.workconnect.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-subscription")
@PreAuthorize("hasRole('Applicant')")
public class UserSubscriptionController {
    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    public ResponseEntity<List<UserSubscriptionDTO>> listAllPurchases() {
        List<UserSubscriptionDTO> purchases = userSubscriptionService.getAllUserSubscription();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionDTO> createPurchase(@RequestBody UserSubscriptionCreateUpdateDTO userSubscriptionCreateUpdateDTO) {
        UserSubscriptionDTO savedPurchase = userSubscriptionService.createUserSubscription(userSubscriptionCreateUpdateDTO);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    //@GetMapping("/user/{userId}")
    //public ResponseEntity<List<PurchaseDTO>> getPurchaseHistory(@PathVariable Integer userId) {
    @GetMapping("/user")
    public ResponseEntity<List<UserSubscriptionDTO>> getPurchaseHistory() {
        List<UserSubscriptionDTO> purchaseHistory = userSubscriptionService.getUserSubscriptionHistoryByUserId();
        return ResponseEntity.ok(purchaseHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSubscriptionDTO> getPurchaseById(@PathVariable Integer id) {
        UserSubscriptionDTO purchase = userSubscriptionService.getUserSubscriptionById(id);
        return ResponseEntity.ok(purchase);
    }

    // Endpoint para confirmar la compra (calcular total)
    @PutMapping("/confirm/{id}")
    public ResponseEntity<UserSubscriptionDTO> confirmPurchase(@PathVariable Integer id) {
        UserSubscriptionDTO confirmedPurchase = userSubscriptionService.confirmUserSubscription(id);
        return ResponseEntity.ok(confirmedPurchase);
    }
}
