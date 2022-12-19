package ar.lamansys.app.jupiter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;

public class SgxAsserts {
	private SgxAsserts() {
		// Métodos utilitarios para test JUnit 5
	}

	public static <T> T assertPresent(final Optional<T> optional) {
		Assertions.assertNotNull(optional);
		assertTrue(optional.isPresent());
		return optional.get();
	}
}
