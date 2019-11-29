package com.example.demo.repo;

import com.example.demo.model.Univer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniverRepo extends JpaRepository<Univer, Long> {

    @Query(value = "select u from Univer u where u.name = :name")
    Univer findByName(String name);

}
