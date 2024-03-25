package ssvv.repository;

import ssvv.domain.Student;
import ssvv.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

