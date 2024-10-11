package com.workconnect.repository;

import com.workconnect.model.entity.CommentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentsGroupRepository extends JpaRepository<CommentsGroup,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentsGroup cg WHERE cg.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Integer memberId);

}
