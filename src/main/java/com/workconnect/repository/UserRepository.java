package com.workconnect.repository;

public interface UserRepository {
import com.workconnect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


}
