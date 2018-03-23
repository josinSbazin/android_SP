package ru.com.rh.sp.data;

/**
 * Единицы измерения массы
 */

public enum WeightUnits {
    KG("кг"),
    T("т");

    private String name;
    WeightUnits(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
