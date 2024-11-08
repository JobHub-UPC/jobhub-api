package com.workconnect.api;

import com.workconnect.dto.SubscriptionPlanDetailsDTO;
import com.workconnect.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanController {
    private final SubscriptionPlanService subscriptionPlanService;
    @GetMapping
    public ResponseEntity<List<SubscriptionPlanDetailsDTO>> listAll() {
        List<SubscriptionPlanDetailsDTO> subscriptionPlans = subscriptionPlanService.getSubscriptionPlanDetails();
        return new ResponseEntity<>(subscriptionPlans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlanDetailsDTO> getById(@PathVariable Integer id) {
        SubscriptionPlanDetailsDTO subscriptionPlan = subscriptionPlanService.getSubscriptionPlanDetailsById(id);
        return new ResponseEntity<>(subscriptionPlan, HttpStatus.OK);
    }
}
