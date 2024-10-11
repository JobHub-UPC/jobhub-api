package com.workconnect.service.impl;

import com.workconnect.dto.UserSubscriptionCreateUpdateDTO;
import com.workconnect.dto.UserSubscriptionDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.UserSubscriptionMapper;
import com.workconnect.model.entity.SubscriptionPlan;
import com.workconnect.model.entity.User;
import com.workconnect.model.entity.UserSubscription;
import com.workconnect.model.enums.PaymentStatus;
import com.workconnect.repository.SubscriptionPlanRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.repository.UserSubscriptionRepository;
import com.workconnect.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserSubscriptionMapper userSubscriptionMapper;
    private final UserRepository userRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    @Override
    public UserSubscriptionDTO createUserSubscription(UserSubscriptionCreateUpdateDTO userSubscriptionCreateUpdateDTO) {
        UserSubscription userSubscription =userSubscriptionMapper.toEntity(userSubscriptionCreateUpdateDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =null;
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }
        userSubscription.setUser(user);
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(userSubscriptionCreateUpdateDTO.getSubscriptionPlan_id())
                .orElseThrow(ResourceNotFoundException::new);
        userSubscription.setSubscriptionPlan(subscriptionPlan);
        userSubscription.setStartDate(LocalDate.now());
        userSubscription.setEndDate(LocalDate.now().plusDays(30*(userSubscription.getMonths())));
        userSubscription.setLastUpdated(LocalDateTime.now());
        userSubscription.setActive(false);
        userSubscription.setPaymentStatus(PaymentStatus.PENDING);
        userSubscription.setAmount(subscriptionPlan.getPrice()*userSubscription.getMonths());
        UserSubscription userSubscription1= userSubscriptionRepository.save(userSubscription);
        return userSubscriptionMapper.toDTO(userSubscription1);
    }

    @Override
    public List<UserSubscriptionDTO> getUserSubscriptionHistoryByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }
        return userSubscriptionRepository.findByUserId(user.getId()).stream().map(userSubscriptionMapper::toDTO).toList();
    }

    @Override
    public List<UserSubscriptionDTO> getAllUserSubscription() {
        return userSubscriptionRepository.findAll().stream().map(userSubscriptionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserSubscriptionDTO confirmUserSubscription(Integer userSubscriptionId) {
        UserSubscription userSubscription=userSubscriptionRepository.findById(userSubscriptionId)
                .orElseThrow(()->new ResourceNotFoundException("User Subscription not found"));
        userSubscription.setPaymentStatus(PaymentStatus.PAID);
        userSubscription.setActive(true);
        UserSubscription userSubscription1= userSubscriptionRepository.save(userSubscription);
        return userSubscriptionMapper.toDTO(userSubscription1);
    }

    @Override
    public UserSubscriptionDTO getUserSubscriptionById(Integer id) {
        UserSubscription userSubscription=userSubscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User Subscription not found"));
        return userSubscriptionMapper.toDTO(userSubscription);
    }
}
