package com.workconnect.service.impl;

import com.workconnect.dto.SubscriptionPlanDetailsDTO;
import com.workconnect.mapper.SubscriptionPlanMapper;
import com.workconnect.model.entity.SubscriptionPlan;
import com.workconnect.repository.SubscriptionPlanRepository;
import com.workconnect.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
private final SubscriptionPlanRepository subscriptionPlanRepository;
private final SubscriptionPlanMapper subscriptionPlanMapper;

    @Override
    public List<SubscriptionPlanDetailsDTO> getSubscriptionPlanDetails() {
        return subscriptionPlanRepository.findAll().stream().map(subscriptionPlanMapper::toDetailsDto).collect(Collectors.toList());
    }

    @Override
    public SubscriptionPlanDetailsDTO getSubscriptionPlanDetailsById(Integer id) {
        SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(id).orElseThrow(()->new RuntimeException("Subscription plan not found with id: "+id));
        return subscriptionPlanMapper.toDetailsDto(subscriptionPlan);
    }
}
