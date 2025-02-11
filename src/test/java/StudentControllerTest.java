import com.student.core.College;
import com.student.core.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class StudentControllerTest {

    private HttpHeaders headers;
    private String URL;

    @BeforeEach
    void setUp() {
        URL = "http://localhost:8081/college/student";
        headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testAdd() {
        Student student = new Student(1, "firstName", "surname", "dept", 20.00);
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(URL, new HttpEntity<>(student, headers), String.class);
        String locationUrl = responseEntity.getHeaders().get("location").get(0);
        ResponseEntity<Student> response = new RestTemplate().getForEntity("http://localhost:8081" + locationUrl, Student.class);
        System.out.println(response.getBody());
    }

    @Test
    void testAddNegative() {
        Student student = new Student(0, null, "surname", "dept", 20.00);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(URL, new HttpEntity<>(student, headers), String.class);
            assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        });
    }

    @Test
    void testGetAll() {
        ResponseEntity<Collection<Student>> responseEntity =
                new RestTemplate().exchange(URL, HttpMethod.GET, null, new
                        ParameterizedTypeReference<Collection<Student>>() {
                        });
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        responseEntity.getBody().forEach(p -> {
            System.out.println(p);
        });
    }

    @Test
    void testGetOnePathParam() {
        ResponseEntity<Student> responseEntity =
                new RestTemplate().exchange(URL + "/{id}", HttpMethod.GET, null, Student.class, 3);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(responseEntity.getBody());
    }

    @Test
    void testGetOneRequestParam() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("accept", "application/json");
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(URL + "/single?id={id}", HttpMethod.GET, new HttpEntity<String>(httpHeaders), String.class, 3);
        System.out.println(responseEntity.getBody());
    }

    @Test
    void testAddStudent() {
        Student student = new Student(0, "Susan", "Doubtfire", "French", 75.00, new College("1", "1", "1", "1"));

        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(URL, new HttpEntity<Student>(student, headers), String.class);

        String locationUrl = responseEntity.getHeaders().get("location").get(0);

        ResponseEntity<Student> response = new RestTemplate().getForEntity("http://localhost:8081" + locationUrl, Student.class);
    }

}
