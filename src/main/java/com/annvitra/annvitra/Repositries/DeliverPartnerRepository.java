package com.annvitra.annvitra.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annvitra.annvitra.Entity.DeliveryPartner;

@Repository
public interface  DeliverPartnerRepository extends JpaRepository<DeliveryPartner, Long> {

    public DeliveryPartner findByUser_Mobile(String mobile);
    
}
