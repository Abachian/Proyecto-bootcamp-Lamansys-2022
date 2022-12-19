package ar.lamansys.sgx.shared.featureflags.application;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum AppFeature implements Feature {

    @Label("Empty")
    EMPTY;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}