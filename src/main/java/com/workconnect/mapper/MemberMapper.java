package com.workconnect.mapper;

import com.workconnect.dto.MemberCreateUpdateDTO;
import com.workconnect.dto.MemberDTO;
import com.workconnect.dto.MemberReportDTO;
import com.workconnect.model.entity.Member;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    private final ModelMapper modelMapper;

    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    //Mapeo mostrar info
    public MemberReportDTO toDetailsDto(Member member) {
        MemberReportDTO dto = modelMapper.map(member, MemberReportDTO.class);

        //Mapeo manual
        dto.setRole(member.getUser().getRole().getName().name());
        dto.setIsActive(member.getUser().getActive());
        dto.setMemberMail(member.getUser().getEmail());
        dto.setCommunityName(member.getComunity().getName());
        //dto.setJoinDate(member.getJoinDate());
        return dto;
    }

    public Member toEntity(MemberCreateUpdateDTO memberCreateUpdateDTO) {
        return modelMapper.map(memberCreateUpdateDTO, Member.class);
    }

    public MemberCreateUpdateDTO toCreateUpdate(Member member) {
        return modelMapper.map(member, MemberCreateUpdateDTO.class);
    }


}
