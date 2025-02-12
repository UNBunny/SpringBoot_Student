import com.student.StudentApplication;
import com.student.core.Student;
import com.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = StudentApplication.class)
public class StudentServiceTest {
	
	@Autowired
	private StudentService service;
	
	@Test
	void testServiceSingle() {
		Student student =  service.get(1L);
		 assertThat(student.getFirstName(), equalTo("Eric"));
	     assertThat(student.getSurname(), equalTo("Colbert"));
	}
	
	@Test
	void testService() {
		Collection<Student> students =  service.getAllStudents();
		students.forEach(p-> {
				System.out.printf("%-10s %-10s%n",p.getFirstName(), p.getSurname());
		  });
	}

}
