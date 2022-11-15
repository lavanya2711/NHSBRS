package com.nhs.rest.restwebservice.service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.nhs.rest.restwebservice.respository.SkillsRepository;
import com.nhs.rest.restwebservice.respository.PeopleRepository;
import com.nhs.rest.restwebservice.entity.*;


@RestController
public class SkillPeopleRestService {

	@Autowired 
	private SkillsRepository skillsRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@GetMapping("/peoples")
	public List<People> retrieveAllPeople() {
		return peopleRepository.findAll();
	}
	
	@GetMapping("/skills")
	public List<Skills> retrieveAllSkills() {
		return skillsRepository.findAll();
	}
	
	@GetMapping("/peoples/{id}")
	public Optional<People> retrievePeople(@PathVariable int id) {
		Optional<People> people = peopleRepository.findById(id);

		if (!people.isPresent())
			throw new PeopleNotFoundException("People not exist with id-" + id);

		
		return people;
	}

	@DeleteMapping("/peoples/{id}")
	public void deletePeople(@PathVariable int id) {
		peopleRepository.deleteById(id);
	}
	
	@DeleteMapping("/skills/{id}")
	public void deleteSkills(@PathVariable int id) {
		skillsRepository.deleteById(id);
	}
	
	@PostMapping("/peoples")
	public ResponseEntity<Object> createPeople (@Valid @RequestBody People people) {
		People savedPeople = peopleRepository.save(people);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPeople.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/peoples/{id}")
	public void updatePeople (@PathVariable Integer id,@Valid @RequestBody People people) {
		Optional<People> peopleOptional = peopleRepository.findById(id);
		
		if(!peopleOptional.isPresent()) {
			throw new PeopleNotFoundException("People not exist with id-" + id );
		}
		
		People savedPeople = peopleRepository.save(people);

	}
	
	@GetMapping("/peoples/{id}/skills")
	public List<Skills> retrieveAllPeopleSkills(@PathVariable int id) {
		Optional<People> peopleOptional = peopleRepository.findById(id);
		
		if(!peopleOptional.isPresent()) {
			throw new PeopleNotFoundException("People not exist with id-" + id);
		}
		
		return peopleOptional.get().getSkills();
	}
	
	@PostMapping("/peoples/{id}/skills")
	public ResponseEntity<Object> createSkills(@PathVariable int id, @RequestBody Skills skills) {
		
		Optional<People> peopleOptional = peopleRepository.findById(id);
		
		if(!peopleOptional.isPresent()) {
			throw new PeopleNotFoundException("People not exist with id-" + id);
		}

		People people = peopleOptional.get();
		
		skills.setPeople(people);
		
		skillsRepository.save(skills);
		
	   URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(skills.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/skills/{id}")
	public void updateSkills(@PathVariable int id) {
		Optional<Skills> skillsOptional =skillsRepository.findById(id);
		
		if(!skillsOptional.isPresent()) {
			throw new SkillNotFoundException("Skills not exist with id-" + id);
		}
		
		skillsRepository.save(skillsOptional.get());
	}
	
	@PutMapping("/peoples/{id}/skills")
	public void updatePeopleSkills(@PathVariable int id, @RequestBody Skills skills) {
		
		Optional<People> peopleOptional = peopleRepository.findById(id);
		
		if(!peopleOptional.isPresent()) {
			throw new PeopleNotFoundException("People not exist with id-" + id);
		}

		People people = peopleOptional.get();
		
		skills.setPeople(people);
		
		skillsRepository.save(skills);

	}

	
}
