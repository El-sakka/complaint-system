package com.example.complaint.repository;

import com.example.complaint.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM users u WHERE u.user_name= :username", nativeQuery = true)
    Boolean existsByUserName(@Param("username") String userName);

    @Query(value = "SELECT * FROM users WHERE user_name= :username",nativeQuery = true)
    Optional<User> findByUserName(@Param("username") String userName);

}
