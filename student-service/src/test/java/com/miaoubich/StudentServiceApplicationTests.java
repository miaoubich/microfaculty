package com.miaoubich;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.miaoubich.controller.StudentController;
import com.miaoubich.entity.Student;
import com.miaoubich.repository.StudentRepository;
import com.miaoubich.request.CreateStudentRequest;
import com.miaoubich.response.StudentResponse;
import com.miaoubich.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
class StudentServiceApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(StudentServiceApplicationTests.class);
	@Autowired
	private StudentController studentController;
	@MockBean
	private StudentService studentService;
	@MockBean
	private StudentRepository studentRepository;
	
	private long studentId = 5;
	
	@Test
	void addStudentTest() {
		StudentResponse createStudent =new StudentResponse(buildStudent());
		CreateStudentRequest studentRequest = createStudentRequest();
		
		when(studentService.addStudent(studentRequest)).thenReturn(createStudent);
		StudentResponse createStudentResponse = studentController.createStudent(studentRequest);
		
		assertEquals(createStudentResponse, createStudent);
		
	}

	@Test
	void getStudentsTest() {
		int httpStatus = studentController.getAllStudents().getStatusCodeValue();
		
//		when(studentRepository.findAll()).thenReturn(Stream.of(student1, student2).collect(Collectors.toList()));
		when(studentService.getStudents()).thenReturn(listOfStudents());
		
		assertEquals(2, studentController.getAllStudents().getBody().size());
		assertEquals(302, httpStatus);
	}
	
//	@Test
	public void getStudentsResponseEntityTest() {
		ResponseEntity<List<Student>> responseEntity = ResponseEntity.status(HttpStatus.FOUND).build();
		
	}
	
	private Student buildStudent() {
		Student student = new Student();
		
		student.setId(studentId);
		student.setFirstname("Darin");
		student.setLastname("Bouzar");
		student.setEmail("ali@bouzar.org");
		
		return student;
	}
	
	private CreateStudentRequest createStudentRequest() {
		CreateStudentRequest studentRequest = new CreateStudentRequest();
		
		studentRequest.setFirstname("Darin");
		studentRequest.setLastname("Bouzar");
		studentRequest.setEmail("ali@bouzar.org");
		studentRequest.setAddressId((long) 6);
		
		return studentRequest;
	}

	private List<Student> listOfStudents(){
		List<Student> students = new ArrayList<>();
		
		Student student1 = new Student();
		student1.setFirstname("Ali");
		student1.setLastname("Bouzar");
		student1.setEmail("ali@bouzar.org");
		student1.setAddressId((long) 6);
		
		
		Student student2 = new Student();
		student2.setFirstname("Yeah");
		student2.setLastname("CNN");
		student2.setEmail("cnn@bouzar.org");
		student2.setAddressId((long) 5);
		
		students.add(student1);
		students.add(student2);
		
		return students;
	}
}
