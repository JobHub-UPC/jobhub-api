package com.workconnect.repository;

import com.workconnect.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByApplicantId(Integer applicantId); // Para obtener las aplicaciones de un aplicante

    void deleteByApplicantId(Integer applicantId); // Para eliminar aplicaciones por applicantId
}
