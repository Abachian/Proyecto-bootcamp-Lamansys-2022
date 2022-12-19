package ar.lamansys.app;

import ar.lamansys.sgx.shared.auditable.entity.SGXAuditableEntity;
import org.assertj.core.api.Assertions;

public class TestUtils {

	private TestUtils() {
		
	}
	
	public static void assertCreateAuditableEntity(SGXAuditableEntity e) {
	
		Assertions.assertThat(e.getCreatedOn())
			.isNotNull();

		Assertions.assertThat(e.getUpdatedOn())
				.isNotNull();
	}
	
	public static void assertUpdateAuditableEntity(SGXAuditableEntity e) {
		Assertions.assertThat(e.getUpdatedOn())
			.isNotNull();	
	}
}
