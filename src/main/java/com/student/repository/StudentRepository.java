package com.student.repository;

import com.student.core.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByDept(String department);

    Collection<Student> getAllByDept(String dept);
}
