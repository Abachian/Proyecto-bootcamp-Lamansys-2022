package ar.lamansys.sgx.shared.flavor;


import ar.lamansys.sgx.shared.featureflags.states.InitialFeatureStates;
import ar.lamansys.sgx.shared.flavor.instances.RootFeatureStates;

public class InitialFeatureStatesStrategy {
	private InitialFeatureStatesStrategy() {
		// utility class
	}

	public static InitialFeatureStates forFlavor(FlavorBo flavor) {
		switch (flavor) {
			case ROOT: return new RootFeatureStates();
		}
		throw new RuntimeException(String.format("Missing InitialFeatureStates for flavor %s", flavor));
	}

}
