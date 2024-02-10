package com.example.complaint.repository;

import com.example.complaint.model.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,String> {

    @Query(value = "SELECT * FROM complaints WHERE user_id= :userId",nativeQuery = true)
    List<Complaint> findAllByUserId(@Param("userId") String userId);
}
