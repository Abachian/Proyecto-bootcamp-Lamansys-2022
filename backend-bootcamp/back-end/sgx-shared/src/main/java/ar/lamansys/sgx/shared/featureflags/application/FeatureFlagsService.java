package ar.lamansys.sgx.shared.featureflags.application;

public interface FeatureFlagsService {

    boolean isOn(AppFeature feature);
}
