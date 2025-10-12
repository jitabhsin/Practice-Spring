package com.example.jsonapi.repository;

import com.example.jsonapi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User , Long> {

}
