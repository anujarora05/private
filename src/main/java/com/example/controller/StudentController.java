package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		//student payload must be deserialisable
		return studentService.createStudent(student);
	}
	@GetMapping("/get/{id}")
	public Student logInfo(@PathVariable String id) {
		return studentService.getStudentById(id);
	}
	@Operation(summary = "This endpoint fetches all the student records")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description ="Fetched All the student records",
					content= {@Content(mediaType = "application/json")}
					),
			@ApiResponse(
					responseCode = "404",
					description = "Not Available",
					content = @Content
					)
	})
	@GetMapping("/all")
	public List<Student> getAllStudent(){
		return studentService.getAllStudents();
	}
	@PutMapping("/update")
	public Student updateDetails(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable String id) {
		return studentService.deleteStudent(id);
	}
	
	@GetMapping("/getByName/{name}")
	public List<Student> getStudentByName(@PathVariable String name) {
		return studentService.getStudentByName(name);
	}
	
	@GetMapping("/getByNameEmail")
	public List<Student> getStudentByNameAndEmail(@RequestParam String name , @RequestParam String email){
		return studentService.getStudentByNameAndEmail(name,email);
	}
	
	@GetMapping("/partial")
	public List<Student> getPartialStudents(@RequestParam int pageNo, @RequestParam int pageLimit){
		return studentService.getPartialStudentRecords(pageNo,pageLimit);
	}
	@GetMapping("/allSorted")
	public List<Student> getSortedResult(){
		return studentService.getSortedResults();
	}
	@GetMapping("/byDeptName")
	public List<Student> getByDeptName(@RequestParam String deptName){
		return studentService.getByDeptName(deptName);
	}
	@GetMapping("/bySubName")
	public List<Student> getBySubjectName(@RequestParam String subName){
		return studentService.getBySubjectName(subName);
	}
	@GetMapping("/emailLike")
	public List<Student> getByEmailLike(@RequestParam String email){
		return studentService.getByEmailLike(email);
	}
	@GetMapping("/startsWith")
	public List<Student> getByNameStartsWIth(String name){
		return studentService.getByNameStartingWith(name);
	}
}


