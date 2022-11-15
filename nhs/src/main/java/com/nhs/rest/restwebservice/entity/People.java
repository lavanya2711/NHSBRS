package com.nhs.rest.restwebservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhs.rest.restwebservice.validator.SkillLevel;

@Entity
public class People {
	
	@Id
	@GeneratedValue
	private Integer id;
	@SkillLevel
    @NotNull(message = "Please provide a skill level")
	private String skillLevel;
	private String name;

	@OneToMany(mappedBy="people")
	private List<Skills> skills;
	
	protected People() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", skillLevel=" + skillLevel + ", name=" + name + ", skills=" + skills + "]";
	}
	


}
