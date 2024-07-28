package com.crud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.demo.Entity.Models;
@Repository
public interface ModelRepository extends JpaRepository<Models, Integer>{

}
