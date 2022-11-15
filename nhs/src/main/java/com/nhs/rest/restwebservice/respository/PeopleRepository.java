package com.nhs.rest.restwebservice.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhs.rest.restwebservice.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer>{

}
