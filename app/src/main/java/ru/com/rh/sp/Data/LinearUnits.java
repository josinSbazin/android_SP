package ru.com.rh.sp.Data;

/**
 * Линейные единцы измерения
 */

public enum LinearUnits {
    MM("мм", 0.001d),
    SM("см", 0.01d),
    DM("дм", 0.1d),
    M("м", 1d),
    KM("км", 1000d);

    private String name;
    private double partOfMeter;

    LinearUnits(String name, double partOfMeter) {
        this.name = name;
        this.partOfMeter = partOfMeter;
    }

    public double getPartOfMeter() {
        return partOfMeter;
    }

    @Override
    public String toString() {
        return name;
    }
}

//<!--Единцы измерения-->
//<string-array name="length_units">
//<item>мм</item>
//<item>см</item>
//<item>дм</item>
//<item>м</item>
//<item>км</item>
//</string-array>

