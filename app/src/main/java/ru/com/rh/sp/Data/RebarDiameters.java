package ru.com.rh.sp.Data;

/**
 * Арматура соответствующих диаметров (в мм) по СП 63.13330.2012
 */
public enum RebarDiameters {
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _10(10),
    _12(12),
    _14(14),
    _16(16),
    _18(18),
    _20(20),
    _22(22),
    _25(25),
    _28(28),
    _32(32),
    _36(36),
    _40(40);

    //Плотность стали в кг/м3
    private final static double DENSITY = 7850d;
    private double diameter;
    private double area;
    private double weightPerMeter;

    /**
     * Диаметр арматуры
     * @return диаметр арматуры в мм
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Площадь арматуры
     * @return площадь арматуры в мм2
     */
    public double getArea() {
        return area;
    }

    /**
     * Погонная масса арматуры
     * @return масса арматуры в кг/м
     */
    public double getWeightPerMeter() {
        return weightPerMeter;
    }

    RebarDiameters(int diameter) {
        this.diameter = diameter;
        this.area = Math.PI * Math.pow(diameter / 2d, 2d);
        this.weightPerMeter = DENSITY * area * 1e-6d;
    }

    @Override
    public String toString() {
        return "Ø " + (int)diameter;
    }
}
