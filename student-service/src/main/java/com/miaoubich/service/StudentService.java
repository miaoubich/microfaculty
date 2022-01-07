package com.miaoubich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaoubich.entity.Student;
import com.miaoubich.repository.StudentRepository;
import com.miaoubich.request.CreateStudentRequest;
import com.miaoubich.response.StudentResponse;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public StudentResponse addstudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstname(createStudentRequest.getFirstname());
		student.setLastname(createStudentRequest.getLastname());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		return new StudentResponse(student);
	}

	public void addStudentList(List<Student> students) {
		studentRepository.saveAll(students);
	}
	
	public StudentResponse getStudentById(long studentId) {
		return new StudentResponse(studentRepository.findById(studentId).get());
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Student EditAddress(Student student) {
		Student existStudent = studentRepository.getById(student.getId());
		
		existStudent.setFirstname(student.getFirstname());
		existStudent.setLastname(student.getLastname());
		existStudent.setEmail(student.getEmail());
		existStudent.setAddressId(student.getAddressId());
		
		return studentRepository.save(existStudent);
	}

	public void deleteStudent(Long studentId) {
		studentRepository.deleteById(studentId);
	}
	
	
}
