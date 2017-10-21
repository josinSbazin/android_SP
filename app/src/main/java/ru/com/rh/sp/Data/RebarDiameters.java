package ru.com.rh.sp.Data;

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

    private int diameter;

    RebarDiameters(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "Ø " + diameter;
    }
}
