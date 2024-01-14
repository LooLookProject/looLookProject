package com.joy.llkproject.repository;

import com.joy.llkproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository2 extends JpaRepository<User,String> {



    
}
