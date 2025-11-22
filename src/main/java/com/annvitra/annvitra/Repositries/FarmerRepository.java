package com.annvitra.annvitra.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annvitra.annvitra.Entity.Farmer;

@Repository
public interface  FarmerRepository extends JpaRepository<Farmer, Long>{

    Farmer findByUser_Mobile(String mobile);
    
}
