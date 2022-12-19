package ar.lamansys.sgx.shared.flavor.instances;


import ar.lamansys.sgx.shared.featureflags.application.AppFeature;
import ar.lamansys.sgx.shared.featureflags.states.InitialFeatureStates;

import java.util.EnumMap;

public class RootFeatureStates implements InitialFeatureStates {

	@Override
	public EnumMap<AppFeature, Boolean> getStates() {
		EnumMap<AppFeature, Boolean> map = new EnumMap<>(AppFeature.class);

		map.put(AppFeature.EMPTY, true);
		return map;
	}

}