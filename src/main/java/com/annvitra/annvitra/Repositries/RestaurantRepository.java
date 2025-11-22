package com.annvitra.annvitra.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.annvitra.annvitra.Entity.Restaurant;

@Repository
public interface  RestaurantRepository extends JpaRepository<Restaurant, Long>{

    public Restaurant findByUser_Mobile(String mobile);
    
}
