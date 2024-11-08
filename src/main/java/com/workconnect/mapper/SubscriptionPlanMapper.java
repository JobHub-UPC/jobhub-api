package com.workconnect.mapper;

import com.workconnect.dto.SubscriptionPlanDetailsDTO;
import com.workconnect.model.entity.SubscriptionPlan;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPlanMapper {
private final ModelMapper modelMapper;
public SubscriptionPlanMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
}
    public SubscriptionPlanDetailsDTO toDetailsDto(SubscriptionPlan subscriptionPlan){
        SubscriptionPlanDetailsDTO subscriptionPlanDetailsDTO=modelMapper.map(subscriptionPlan,SubscriptionPlanDetailsDTO.class);
        return subscriptionPlanDetailsDTO;
    }
}
