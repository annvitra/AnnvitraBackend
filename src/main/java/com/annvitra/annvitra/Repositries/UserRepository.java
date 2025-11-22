package com.annvitra.annvitra.Repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annvitra.annvitra.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    public User findByMobile(String mobile);

}
