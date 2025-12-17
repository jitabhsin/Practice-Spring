package com.example.GraphQl.repository;


import com.example.GraphQl.entity.*;

import org.springframework.data.jpa.repository.*;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book , Long>{




}

 

