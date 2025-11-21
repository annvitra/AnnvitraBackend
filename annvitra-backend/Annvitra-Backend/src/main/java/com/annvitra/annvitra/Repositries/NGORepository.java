package com.annvitra.annvitra.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.annvitra.annvitra.Entity.Ngo;

@Repository
public interface  NGORepository extends JpaRepository<Ngo, Long>{
    
}
