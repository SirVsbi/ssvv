import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;


public class Test {

    @org.junit.Test
    @DisplayName("Add new student")
     public void addNew() {

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        service.deleteStudent("1234");


        Assertions.assertEquals(
                service.saveStudent("1234", "Test Student", 937), 1);
    }

    @org.junit.Test
    @DisplayName("Add Existing student")
    public void addExisting() {

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        service.deleteStudent("1234");

        service.saveStudent("1234", "Test Student", 937);

        Assertions.assertEquals(
                service.saveStudent("1234", "Test Student", 937), 0);
    }
    }
