package com.company.race_conditions;

public class SafeLazyInit {

    private static SafeLazyInit INSTANCE = null;

    private SafeLazyInit() {

    }

    // Disable more than one thread to access this method simultaneously
    // make the lazy instantiation thread safe
    public static synchronized SafeLazyInit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SafeLazyInit();
        }
        return INSTANCE;
    }

}
