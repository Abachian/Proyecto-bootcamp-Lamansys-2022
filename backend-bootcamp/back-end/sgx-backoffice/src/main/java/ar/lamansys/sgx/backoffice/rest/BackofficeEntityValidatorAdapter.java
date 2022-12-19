package ar.lamansys.sgx.backoffice.rest;

import ar.lamansys.sgx.backoffice.validation.BackofficeEntityValidator;

public class BackofficeEntityValidatorAdapter<E, I> implements BackofficeEntityValidator<E, I>{

	@Override
	public void assertCreate(E entity) {
		// Do nothing
	}

	@Override
	public void assertUpdate(I id, E entity) {
		// Do nothing
	}

	@Override
	public void assertDelete(I id) {
		// Do nothing
	}

}
