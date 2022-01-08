package com.miaoubich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.miaoubich.entity.Student;
import com.miaoubich.repository.StudentRepository;
import com.miaoubich.request.CreateStudentRequest;
import com.miaoubich.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private WebClient webClient;

	public StudentResponse addstudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstname(createStudentRequest.getFirstname());
		student.setLastname(createStudentRequest.getLastname());
		student.setEmail(createStudentRequest.getEmail());

		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);

		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		return studentResponse;
	}

	public void addStudentList(List<Student> students) {
		studentRepository.saveAll(students);
	}

	public StudentResponse getStudentById(long studentId) {
		Student student = studentRepository.findById(studentId).get();
		StudentResponse studentResponse = new StudentResponse(student);

		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		return studentResponse;
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

	public AddressResponse getAddressById(long addressId) {
		Mono<AddressResponse> address = webClient.get().uri("/" + addressId).retrieve()
				.bodyToMono(AddressResponse.class);

		return address.block();
	}
}
