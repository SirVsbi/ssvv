package ssvv.repository;

import ssvv.domain.Nota;
import ssvv.domain.Pair;
import ssvv.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
