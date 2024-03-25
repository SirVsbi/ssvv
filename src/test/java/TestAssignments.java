
import org.junit.jupiter.api.*;
import ssvv.domain.Nota;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import ssvv.repository.NotaXMLRepository;
import ssvv.repository.StudentXMLRepository;
import ssvv.repository.TemaXMLRepository;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;
import ssvv.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAssignments {


    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();


    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @BeforeAll
    public void setup(){

        var assignments = service.findAllTeme();
        System.out.println(assignments);
    }


    @Test
    @Order(1)
    public void test_addNew() {

        service.deleteTema("1234");
        assertEquals(1,
                service.saveTema("1234", "This is a desciption", 7, 5));
    }



    @Test
    @Order(2)
    public void test_addNew_existing() {

        assertEquals(0,
                service.saveTema("1234", "This is a desciption", 7, 5));
    }


    @Test
    @Order(3)
    public void test_idIsEmpty() {

        assertEquals(1,
                service.saveTema("", "This is a desciption", 7, 5));
    }

    @Test
    @Order(4)
    public void test_descriptionIsEmpty() {

        service.deleteTema("32");

        assertEquals(1,
                service.saveTema("32", "", 7, 5));
    }




}
