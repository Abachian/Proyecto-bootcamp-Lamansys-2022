package ar.lamansys.sgx.shared.featureflags.states;

import ar.lamansys.sgx.shared.featureflags.application.AppFeature;

import java.util.EnumMap;

public interface InitialFeatureStates {
	EnumMap<AppFeature, Boolean> getStates();
}
