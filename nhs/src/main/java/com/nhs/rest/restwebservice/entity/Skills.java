package com.nhs.rest.restwebservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skills {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private People people;

	protected Skills() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Skills [id=" + id + ", name=" + name + ", people=" + people + "]";
	}

}
