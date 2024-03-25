

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
public class TestStudents {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);


    @BeforeAll
    public void setup(){

        var students = service.findAllStudents();
        students.forEach(
                student -> service.deleteStudent(student.getID())
        );
        System.out.println(students);
    }


    @Test
     public void addNew() {

        service.deleteStudent("1234");


        assertEquals(
                service.saveStudent("1234", "main.Test Student", 937), 1);
    }

    @Test
    public void addExisting() {
        service.deleteStudent("1234");

        service.saveStudent("1234", "main.Test Student", 937);

        assertEquals(
                service.saveStudent("1234", "main.Test Student", 937), 0);
    }

    @Test
    public void testSaveStudentBoundary_valid() {
        int result = service.saveStudent("5", "John", 937);
        assertEquals( 0, result);
    }

    @Test
    public void testSaveStudentBoundary_valid2() {
        int result = service.saveStudent("7", "John", 211);
        assertEquals( 0, result);
    }

    @Test
    public void testSaveStudentBoundary_Existing_Student() {
        int result = service.saveStudent("7", "John", 938);
        assertEquals( 1, result);
    }

    @Test
    public void testSaveStudentBoundary_4() {
        // Assuming "maxINT" is the maximum boundary for an integer group
        int result = service.saveStudent("6", "John", Integer.MAX_VALUE);
        assertEquals( 1, result);
    }

    @Test
    public void testSaveStudentBoundary_5() {
        int result = service.saveStudent("4", "John", Integer.MAX_VALUE - 1);
        assertEquals( 1, result);
    }


    // Test for invalid group input (assuming negative groups are invalid)
    @Test
    public void testSaveStudentInvalidGroup() {
        int result = service.saveStudent("2", "John", -1);
        assertEquals( 1, result);
    }


    @Test
    public void testSaveStudent_invalidGroup2() {
        int result = service.saveStudent("2", "John", 3333);
        assertEquals( 1, result);
    }
    @Test
    // Test for invalid name input (assuming null names are invalid)
    public void testSaveStudentInvalidName() {
        int result = service.saveStudent("1", null, 937);
        assertEquals( 1, result);

    }


    @Test
    // Test for invalid id input (assuming null or empty ids are invalid)
    public void testSaveStudentInvalidId() {
        int result = service.saveStudent(null, "John", 937);
        assertEquals( 1, result);

    }
    }
