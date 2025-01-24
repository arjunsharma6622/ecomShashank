package com.shashank.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.ecom.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
