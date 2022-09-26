package com.ugunay.customtoast;

/**
 * Toast penceresinin açık kalma süresiyle ilgili sabitleri barındırır.
 * Sabitlerin değerleri <b>milisaniye</b> cinsindendir.
 */
public enum Duration {

    EX_SHORT(1_000),
    SHORT(2_000),
    LONG(3_000),
    EX_LONG(5_000);

    final int value;

    Duration(int value) {
        this.value = value;
    }

}
