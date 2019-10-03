package com.in28minutes.springboot.rest.example.student;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// @RestController : Combination of @Controller and @ResponseBody. Beans returned are converted to/from JSON/XML.

@RestController
public class StudentResource {

	// Autowire : we can retrieve and save data to database.
	@Autowired
	private StudentRepository studentRepository;

	// using Postman: test with Get request :
	// http://localhost/8080/students
	// without body
	// returns json
	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentRepository.findAll();
	}

	// using Postman: test with Get request :
	// http://localhost/8080/students/10002
	// without body
	// returns json
	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent()) {
			throw new StudentNotFoundException("id-" + id);
		}
		return student.get();
	}

	// using Postman: test with Delete request :
	// http://localhost/8080/students/10002
	// without body
	// returns status 200 if success
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}

	// using Postman: test with Post request :
	// http://localhost/8080/students
	// with body
	// returns status 201 if success
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// using Postman: test with Put request :
	// http://localhost/8080/students/1002
	// with body
	// returns status 201 if success
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		student.setId(id);
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}

}
