package ssvv.repository;

import ssvv.domain.Tema;
import ssvv.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

