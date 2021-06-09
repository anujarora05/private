package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;
import java.util.List;


@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
	public List<Student> findByName(String name);
	public List<Student> findByNameAndEmail(String name, String email);//order should be same as in method name
	public List<Student> findByNameOrEmail(String name, String email);
	public List<Student> findByDepartmentDeptName(String deptName); //corresponds to department.deptName
	public List<Student> findBySubjectsSubName(String subName);
	public List<Student> findByEmailIsLike(String email);
	public List<Student> findByNameStartsWith(String name);
}
