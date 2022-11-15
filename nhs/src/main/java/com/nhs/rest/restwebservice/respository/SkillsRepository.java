package com.nhs.rest.restwebservice.respository;

import org.springframework.stereotype.Repository;
import com.nhs.rest.restwebservice.entity.Skills;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface SkillsRepository extends JpaRepository<Skills,Integer> {

}
