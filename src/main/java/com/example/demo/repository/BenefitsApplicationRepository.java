package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BenefitsApplication;
import java.util.List;


@Repository
public interface BenefitsApplicationRepository extends JpaRepository<BenefitsApplication,Long>{
    List<BenefitsApplication> findByEmail(String email);

}
