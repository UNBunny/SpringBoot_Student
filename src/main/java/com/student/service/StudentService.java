package com.student.service;

import com.student.core.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


public interface StudentService {

	Student get(long id);
	Collection<Student> getAllStudents();

	Collection<Student> getStudentsByDepartment(String department);
}
