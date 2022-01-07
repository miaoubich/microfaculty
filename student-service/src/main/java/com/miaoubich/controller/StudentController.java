package com.miaoubich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miaoubich.entity.Student;
import com.miaoubich.request.CreateStudentRequest;
import com.miaoubich.response.StudentResponse;
import com.miaoubich.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public StudentResponse createStudent(@RequestBody CreateStudentRequest studentRequest) {
		return studentService.addstudent(studentRequest);
	}

	@PostMapping("/addList")
	public ResponseEntity<String> addStudents(@RequestBody List<Student> students) {
		studentService.addStudentList(students);
		return new ResponseEntity<String>("Student list data stored successfully.", HttpStatus.CREATED);
	}

	@GetMapping("/{studentId}")
	public StudentResponse printSingleStudent(@PathVariable Long studentId) {
		return studentService.getStudentById(studentId);
	}

	@GetMapping("/getList")
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<Student> updateAddress(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.EditAddress(student), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);

		return ResponseEntity.ok("Student successfully deleted!");
	}
}
