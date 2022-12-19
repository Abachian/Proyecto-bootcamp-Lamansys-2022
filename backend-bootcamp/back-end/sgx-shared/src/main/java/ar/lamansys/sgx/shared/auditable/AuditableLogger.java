package ar.lamansys.sgx.shared.auditable;

import org.slf4j.LoggerFactory;

public class AuditableLogger {

	private static String AUDITABLE_LOGGER_NAME = "AUDIT_LOGGER";

	public static void log(String msg) {
		LoggerFactory
		.getLogger(AUDITABLE_LOGGER_NAME)
		.info(msg);
	}
}
