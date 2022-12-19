package ar.lamansys.cart.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoincrementId {
    private static final AtomicInteger count = new AtomicInteger(0);

    static public Long incrementAndGetId() {
        return Long.valueOf(count.incrementAndGet());
    }
}
