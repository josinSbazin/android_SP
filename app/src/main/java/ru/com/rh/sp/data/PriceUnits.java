package ru.com.rh.sp.data;

/**
 * Стоимость за единицу измерения
 */

public enum PriceUnits {
    OF_METERS("за метр"),
    OF_TONNAS("за тонну");

    private String name;

    PriceUnits(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
