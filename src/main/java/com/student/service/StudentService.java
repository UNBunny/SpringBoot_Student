package com.student.service;

import com.student.core.Student;

import java.util.Collection;

public interface StudentService {

	Student get(long id);
	Collection<Student> getAllStudents();

}
