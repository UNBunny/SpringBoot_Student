package com.student.service;

import com.student.core.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student get(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return em.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    @Override
    public Collection<Student> getStudentsByDepartment(String department) {
        TypedQuery<Student> query = em.createQuery("SELECT student FROM Student student Where student.dept = :dept", Student.class);
        query.setParameter("dept", department);
        return query.getResultList();
    }


}
