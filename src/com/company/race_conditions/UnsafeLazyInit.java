package com.company.race_conditions;

public class UnsafeLazyInit {
    private static UnsafeLazyInit INSTANCE = null;

    private UnsafeLazyInit() {

    }

    public static synchronized UnsafeLazyInit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UnsafeLazyInit();
        }
        return INSTANCE;
    }

}
