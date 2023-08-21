package com.example;

public class MathUtil {
    public static int mdc(int a, int b) {
        if (b == 0) return Math.abs(a);
        int resto = a % b;
        return mdc(b, resto);
    }
}
