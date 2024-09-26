package com.workconnect.mapper;

import com.workconnect.dto.MemberDTO;
import com.workconnect.model.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    private final ModelMapper modelMapper;

    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MemberDTO toDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    public Member toEntity(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }


}
