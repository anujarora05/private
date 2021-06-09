package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Student createStudent(Student student) {
		return studentRepository.save(student);		// returned object will have value of id field
	}

	public Student getStudentById(String id) {
		return studentRepository.findById(id).get();
	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		//this student object will have id because it has to update with that particular id
		return studentRepository.save(student);
	}

	public String deleteStudent(String id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
		return "deleted";
	}

	public List<Student> getStudentByName(String name) {
		return studentRepository.findByName(name);
	}

	public List<Student> getStudentByNameAndEmail(String name, String email) {
		// TODO Auto-generated method stub
		return studentRepository.findByNameAndEmail(name, email);
	}

	public List<Student> getPartialStudentRecords(int pageNo, int pageLimit) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageLimit);
		return studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getSortedResults() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.ASC, "name","email"); // 2nd paramter is on which field we want to sort the results
		return studentRepository.findAll(sort);
	}

	public List<Student> getByDeptName(String deptName) {
		return studentRepository.findByDepartmentDeptName(deptName);
	}

	public List<Student> getBySubjectName(String subName) {
		// TODO Auto-generated method stub
		return studentRepository.findBySubjectsSubName(subName);
	}

	public List<Student> getByEmailLike(String email) {
		// TODO Auto-generated method stub
		return studentRepository.findByEmailIsLike(email);
	}
	public List<Student> getByNameStartingWith(String name){
		return studentRepository.findByNameStartsWith(name);
	}

}
