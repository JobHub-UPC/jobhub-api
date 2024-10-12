package com.workconnect.mapper;

import com.workconnect.dto.UserSubscriptionCreateUpdateDTO;
import com.workconnect.dto.UserSubscriptionDTO;
import com.workconnect.model.entity.UserSubscription;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {
    private final ModelMapper modelMapper;
    public UserSubscriptionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public UserSubscription toEntity(UserSubscriptionCreateUpdateDTO userSubscriptionDTO) {
        UserSubscription userSubscription= modelMapper.map(userSubscriptionDTO, UserSubscription.class);
        return userSubscription;
    }
    public UserSubscriptionDTO toDTO(UserSubscription userSubscription) {
        UserSubscriptionDTO userSubscriptionDTO = modelMapper.map(userSubscription, UserSubscriptionDTO.class);
        userSubscriptionDTO.setUser_id(userSubscription.getUser().getId());
        userSubscriptionDTO.setRole(userSubscription.getUser().getRole().getName());
        userSubscriptionDTO.setSubscriptionPlan_id(userSubscription.getSubscriptionPlan().getId());
        return userSubscriptionDTO;
    }
}
